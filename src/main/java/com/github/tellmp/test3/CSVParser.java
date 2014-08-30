package com.github.tellmp.test3;

import java.util.ArrayList;
import java.util.List;

/**
 * This utility class provides an CSV parser, that generates a list of
 * strings out of a given csv format string. This class follows the single
 * responsibility principle providing the parsing functionality.
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class CSVParser {

    /**
     * This method parses a string of n comma-separated values,
     * each value being an date, arbitrary value or a string.
     * The output is generated using the state pattern to determine how to
     * separate the given string.
     *
     * @param input a string of n comma-separated values
     * @return a list of n consecutive lines, where line i contains the ith value of the input
     */
    protected static List<String> parseLineCVS(String input) {
        STATE state = STATE.START;
        List<String> parsedList = new ArrayList<>();
        StringBuilder acc = new StringBuilder();
        char c = ' ';
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            switch (state) {
                case START:
                    if (c >= '0' && c <= '9' || c == '-' || c == ':' || c == '+') {
                        state = STATE.DATE;
                        acc.append(c);
                        break;
                    }
                    if (c == '"') {
                        state = STATE.STRING;
                        break;
                    }
                    if (!Character.isWhitespace(c) && Character.isUnicodeIdentifierPart(c)) {
                        state = STATE.VALUE;
                        acc.append(c);
                        break;
                    }
                    if (!Character.isWhitespace(c)) {
                        throw new IllegalArgumentException("Input: " + c + " is not a valid CSV");
                    }
                    break;
                case DATE:
                    if (c >= '0' && c <= '9' || c == '-' || c == ':' || c == '+' || Character.isWhitespace(c)) {
                        acc.append(c);
                        break;
                    }
                    if (c == ',') {
                        state = STATE.START;
                        parsedList.add(acc.toString());
                        acc.setLength(0);
                        break;
                    }
                    if (!Character.isWhitespace(c)) {
                        throw new IllegalArgumentException("Input: " + c + "  is not a valid CSV");
                    }
                case VALUE:
                    if (c == ',') {
                        state = STATE.START;
                        parsedList.add(acc.toString());
                        acc.setLength(0);
                        break;
                    }
                    if (!Character.isWhitespace(c)) {
                        acc.append(c);
                        break;
                    }
                    if (!Character.isWhitespace(c)) {
                        throw new IllegalArgumentException("Input: " + c + "  is not a valid CSV");
                    }
                case STRING:
                    if (c == '"') {
                        state = STATE.END;
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
                    if (!Character.isWhitespace(c)) {
                        throw new IllegalArgumentException("Input: " + c + "  is not a valid CSV");
                    }
                default:
                    throw new AssertionError("Unknown state " + state);
            }
        }

        if (state == STATE.DATE || state == STATE.VALUE) {
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

    enum STATE {
        START,
        VALUE,
        DATE,
        STRING,
        END
    }

}
