package com.example.crud.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DataLoader {
    private final ObjectMapper objectMapper;

    public DataLoader(ObjectMapper objectMapper, CsvToJsonConverter csvToJsonConverter) {
        this.objectMapper = objectMapper;
    }

    public String loadCsvData(String csvUrl) throws IOException {

        HttpURLConnection connection = null;
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(csvUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return response.toString();
    }

    public String loadJsonData(String jsonUrl) throws IOException {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(jsonUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            Object json = objectMapper.readValue(response.toString(), Object.class);
                String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            return formattedJson;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}