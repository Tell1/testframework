package com.github.tellmp.test1;

/**
 * This class parses the n comma-separated values of a given data source;
 * each value being an integer or a string.
 * The output is n consecutive lines, where line i contains the ith value of the input.
 *
 * Created by Tell Mueller-Pettenpohl on 8/17/14.
 */

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParsing {

    /**
     * This method parses a string of n comma-separated values, each value being an integer or a string.
     * The output is generated using the state pattern to determine how to separate the given string.
     *
     * @param input a string of n comma-separated values
     * @return a list of n consecutive lines, where line i contains the ith value of the input
     */
    public static List<String> parseCVS(String input) {
        STATE state = STATE.START;
        List<String> parsedList = new ArrayList<String>();
        StringBuilder acc = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (state) {
                case START:
                    if (c >= '0' && c <= '9') {
                        state = STATE.INTEGER;
                        acc.append(c);
                        break;
                    }
                    if (c == '"') {
                        state = STATE.STRING;
                        acc.append(c);
                        break;
                    }
                    throw new IllegalArgumentException("Input is not a valid CSV");
                case INTEGER:
                    if (c >= '0' && c <= '9') {
                        acc.append(c);
                        break;
                    }
                    if (c == ',') {
                        state = STATE.START;
                        parsedList.add(acc.toString());
                        acc.setLength(0);
                        break;
                    }
                    throw new IllegalArgumentException("Input is not a valid CSV");
                case STRING:
                    if (c == '"') {
                        state = STATE.END;
                        acc.append(c);
                        parsedList.add(acc.toString());
                        acc.setLength(0);
                        break;
                    }
                    acc.append(c);
                    break;
                case END:
                    if (c == ',') {
                        state = STATE.START;
                        break;
                    }
                    throw new IllegalArgumentException("Input is not a valid CSV");
                default:
                    throw new AssertionError("Unknown state " + state);
            }
        }

        if (state == STATE.INTEGER) {
            // handle end of line case
            parsedList.add(acc.toString());
        } else {
            System.err.print(acc.toString());
            if (state != STATE.START && state != STATE.END) {
                throw new IllegalArgumentException("Input is not a valid CSV");
            }
        }
        return parsedList;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Please change the data in src/main/resources/data.csv" +
                " to define new input for the CSVParser.");

        // In one line ;)
        // Files.lines(Paths.get("data.csv")).map(main.CSVParsing::parseCVS).forEach((list) -> { list.forEach(System.out::println); });
        try (BufferedReader r = Files.newBufferedReader(Paths.get("src/main/resources/data.csv"))) {
            String line;
            while ((line = r.readLine()) != null) {
                for (String str : parseCVS(line)) {
                    System.out.println(str);
                }
            }
        }
    }


    enum STATE {
        START,
        INTEGER,
        STRING,
        END
    }

}