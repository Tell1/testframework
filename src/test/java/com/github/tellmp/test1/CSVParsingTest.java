package com.github.tellmp.test1;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class CSVParsingTest {

    @Test
    public void testParseCVS() throws Exception {
        String[] solution1 = {"2", "6", "3", "2", "5"};
        String[] solution2 = {"\"pears\"", "\"apples\"", "\"walnuts\"", "\"grapes\"", "\"cheese,cake\""};
        String[] solution3 = {"1", "\"Que?\"", "\"Kay?\"", "2", "\"Si.\"", "\"Sea? Kay, sea?\"", "\"No, no, no. Que... ‘what’.\"", "234", "\"Kay Watt?\"", "\"Si, que ‘what’.\"", "\"C.K. Watt?\"", "3", "\"Yes!\"", "\"comma,comma, comma , :)\""};

        assertArrayEquals(CSVParsing.parseCVS("2,6,3,2,5").toArray(), solution1);
        assertArrayEquals(CSVParsing.parseCVS("\"pears\",\"apples\",\"walnuts\",\"grapes\",\"cheese,cake\"").toArray(), solution2);
        assertArrayEquals(CSVParsing.parseCVS("1,\"Que?\",\"Kay?\",2,\"Si.\",\"Sea? Kay, sea?\",\"No, no, no. Que... ‘what’.\",234,\"Kay Watt?\",\"Si, que ‘what’.\",\"C.K. Watt?\",3,\"Yes!\",\"comma,comma, comma , :)\"").toArray(), solution3);
    }
}