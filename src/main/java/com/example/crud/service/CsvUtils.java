package com.example.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvUtils {

    public static List<Map<String, String>> parseCsvData(List<String[]> csvData, String[] headers) {
        List<Map<String, String>> jsonData = new ArrayList<>();

        for (String[] row : csvData) {
            Map<String, String> rowData = new HashMap<>();

            rowData.put("gender", row[0]);
            rowData.put("name.title", row[1]);
            rowData.put("name.first", row[2]);
            rowData.put("name.last", row[3]);
            rowData.put("location.street", row[4]);
            rowData.put("location.city", row[5]);
            rowData.put("location.state", row[6]);
            rowData.put("location.postcode", row[7]);
            rowData.put("location.coordinates.latitude", row[8]);
            rowData.put("location.coordinates.longitude", row[9]);
            rowData.put("location.timezone.offset", row[10]);
            rowData.put("location.timezone.description", row[11]);
            rowData.put("email", row[12]);
            rowData.put("dob.date", row[13]);
            rowData.put("dob.age", row[14]);
            rowData.put("registered.date", row[15]);
            rowData.put("registered.age", row[16]);
            rowData.put("phone", row[17]);
            rowData.put("cell", row[18]);
            rowData.put("picture.large", row[19]);
            rowData.put("picture.medium", row[20]);
            rowData.put("picture.thumbnail", row[21]);

            jsonData.add(rowData);
        }

        return jsonData;
    }
}

