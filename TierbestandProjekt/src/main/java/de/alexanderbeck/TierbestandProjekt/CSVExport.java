package de.alexanderbeck.TierbestandProjekt;


import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

public class CSVExport {

    public String convertToCSV(String[] data) {
        return Stream.of(data)
            .map(this::escapeSpecialCharacters)
            .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    
}