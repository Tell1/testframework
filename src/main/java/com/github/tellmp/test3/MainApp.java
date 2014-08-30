package com.github.tellmp.test3;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Admin on 8/25/14.
 */
public class MainApp {
    SimpleDBConnector simpleDBConnector;


    public static void main(String[] args) {
        try {
            CSVParser.parseCSVFile(new SimpleDBConnector(),
                    new File("src/main/resources/test" +
                    ".csv"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
