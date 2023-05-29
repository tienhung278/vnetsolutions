package com.awaydigitalteams.vnetsolutions.kafka;

import com.awaydigitalteams.vnetsolutions.dto.SaleDTO;
import com.awaydigitalteams.vnetsolutions.service.SaleServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class KafkaConsumer {

    private final SaleServices saleServices;
    private final ObjectMapper mapper;

    public KafkaConsumer(SaleServices saleServices) {
        this.saleServices = saleServices;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JSR310Module());
    }

    @KafkaListener(topics = "topic-1", groupId = "group-1")
    public void consume(String message) throws JsonProcessingException {
        SaleDTO[] saleDTOS = mapper.readValue(message, SaleDTO[].class);
        List<SaleDTO> saleDTOList = Arrays.asList(saleDTOS);
        saleServices.save(saleDTOList);
    }
}
