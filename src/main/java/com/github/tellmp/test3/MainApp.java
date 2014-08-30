package com.github.tellmp.test3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

/**
 * This class is the starting point to use the utility classes CSVParser and
 * write in in the data structure of the SimpleDBConnector.
 * <p/>
 * Created by Admin on 8/25/14.
 */
public class MainApp {

    public static void main(String[] args) throws Exception {
        DBConnector simpleDBConnector = new SimpleDBConnector();

        // Reads lines of the CSV file and applies the parseLineCSV function
        Files.lines(Paths.get("src/main/resources/test.csv")).map
                (CSVParser::parseLineCVS).forEachOrdered((strings) -> {
            try {
                simpleDBConnector.mapCSVData(strings);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        System.out.println(simpleDBConnector);
    }
}
