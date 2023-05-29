package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.dto.SaleDTO;
import com.awaydigitalteams.vnetsolutions.kafka.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class Scheduler {

    @Value("${folder.source}")
    private String baseFolder;
    @Value("${folder.archive}")
    private String archiveFolder;
    private final SaleServices saleServices;
    private KafkaProducer producer;
    private ObjectMapper mapper;

    @Autowired
    public Scheduler(SaleServices saleServices, KafkaProducer producer) {
        this.saleServices = saleServices;
        this.producer = producer;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JSR310Module());
    }

    @Scheduled(fixedRate = 60000)
    private void readFile() throws IOException {
        List<String> list = getListFiles();
        if (list.size() > 0) {
            String file = baseFolder + list.get(0);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<SaleDTO> saleDTOS = new LinkedList<>();
            boolean isFirstLine = true;
            String line;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                saleDTOS.add(createSaleInstance(line));
            }
            reader.close();
            String message = mapper.writeValueAsString(saleDTOS);
            producer.send(message);
            archiveFile(list.get(0));
        }
    }

    private SaleDTO createSaleInstance(String line) {
        String[] words = line.split("\\|");
        return new SaleDTO(words[0],
                words[1],
                words[2],
                Long.parseLong(words[3]),
                new BigDecimal(words[4]));
    }

    private List<String> getListFiles() {
        List<String> list = new LinkedList<>();
        File directoryPath = new File(baseFolder);
        String[] files = directoryPath.list();
        Collections.addAll(list, files);
        return list;
    }

    private void archiveFile(String fileName) throws IOException {
        String source = baseFolder + fileName;
        String destination = archiveFolder + fileName;
        Files.move(Paths.get(source), Paths.get(destination));
    }
}
