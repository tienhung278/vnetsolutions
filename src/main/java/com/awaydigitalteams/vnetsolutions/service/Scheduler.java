package com.awaydigitalteams.vnetsolutions.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {

    List<String> list = new LinkedList<>();
    String baseFolder = "sales/";
    Integer idx = 0;

    public Scheduler() {
        File directoryPath = new File(baseFolder);
        String files[] = directoryPath.list();
        for (String file : files) {
            list.add(file);
        }
    }

    @Scheduled(fixedRate = 60000)
    private void readFile() throws IOException {
        if (idx == list.size()) {
            idx = 0;
        }
        String file = baseFolder + list.get(idx++);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        while (reader.readLine() != null) {

        }
    }
}
