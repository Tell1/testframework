package com.github.tellmp.test3;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class CSVParserTest {

    String[][] testArray = {{"chris@getvero.com, Chris, Hexton, blogger,"},
            {"james@getvero.com, James, Lamont, \"unsubscribed, dead-lead\", 2012-12-31"},
            {"damien@getvero.com, Damien, Brzoska, \"tshirt, warm-lead\", 2013-06-15 00:00:01 +0000"}};
    String[][] solutionArray = {{"chris@getvero.com", "Chris", "Hexton", "blogger"},
            {"james@getvero.com", "James", "Lamont", "unsubscribed, dead-lead", "2012-12-31"},
            {"damien@getvero.com", "Damien", "Brzoska", "tshirt, warm-lead", "2013-06-15 00:00:01 +0000"}};

    @Test
    public void testParseLineCSV() throws Exception {
        for (int i = 0; i < testArray.length; i++) {
            assertEquals(Arrays.asList(solutionArray[i]), CSVParser.parseLineCVS(testArray[i][0]));
            assertArrayEquals(solutionArray[i], CSVParser.parseLineCVS(testArray[i][0]).toArray());
        }
    }
}