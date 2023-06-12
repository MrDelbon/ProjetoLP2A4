package com.example.crud.service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CsvToJsonConverter {

    private final ObjectMapper objectMapper;

    public CsvToJsonConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String convertCsvToJson(String csvFilePath) throws IOException, CsvException {
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(csvFilePath)).withSkipLines(1).build();
        List<String[]> csvData = csvReader.readAll();

        String[] headers = csvData.get(0);
        List<Map<String, String>> jsonData = CsvUtils.parseCsvData(csvData.subList(1, csvData.size()), headers);

        return objectMapper.writeValueAsString(jsonData);
    }
}
