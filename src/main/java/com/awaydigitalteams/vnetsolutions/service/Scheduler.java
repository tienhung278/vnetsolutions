package com.awaydigitalteams.vnetsolutions.service;

import com.awaydigitalteams.vnetsolutions.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class Scheduler {

    String baseFolder = "sales/";
    String archiveFolder = "sales_archive/";
    private final SaleServices saleServices;

    @Autowired
    public Scheduler(SaleServices saleServices) {
        this.saleServices = saleServices;
    }

    @Scheduled(fixedRate = 60000)
    private void readFile() throws IOException {
        List<String> list = getListFiles();
        if (list.size() > 0) {
            String file = baseFolder + list.get(0);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<Sale> sales = new LinkedList<>();
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                sales.add(createSaleInstance(line));
            }
            reader.close();
            archiveFile(list.get(0));
            var newSales = saleServices.getUnitsRevenueByProduct(sales);
            System.out.println(newSales);
        }
    }

    private Sale createSaleInstance(String line) {
        String[] words = line.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return new Sale(LocalDate.parse(words[0], formatter),
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
