package com.github.tellmp.test3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.tellmp.test3.CSVParser.parseLineCVS;
import static com.github.tellmp.test3.Entry.createNewEntry;
import static junit.framework.TestCase.assertFalse;

public class SimpleDBConnectorTest {

    @Test
    public void testMapCSVData() throws Exception {
        SimpleDBConnector simpleDBC = new SimpleDBConnector();
        List<Entry> testEntries = new ArrayList<>();
        testEntries.add(createNewEntry("chris@getvero.com", "Chris", "Hexton",
                "blogger", null, null, null));
        String[] testArray = {"chris@getvero.com, Chris, Hexton, blogger,",
                "james@getvero.com, James, Lamont, \"unsubscribed, dead-lead\", 2012-12-31",
                "damien@getvero.com, Damien, Brzoska, \"tshirt, warm-lead\", 2013-06-15 00:00:01 +0000",
                "chris@getvero.com, Chris, Hexton, developer"};

        for (String testElem : testArray) {
            List<String> parsedCSVData = parseLineCVS(testElem).stream().collect(Collectors.toList());
            simpleDBC.mapCSVData(parsedCSVData);
        }
        assertFalse(simpleDBC.getMappingEntries().contains(testEntries.get(0)));
    }

    @Test
    public void testUpdateEntries() throws Exception {
        SimpleDBConnector simpleDBC = new SimpleDBConnector();
        List<Entry> testEntries = new ArrayList<Entry>();
        int idx = 0;
        testEntries.add(createNewEntry("chris@getvero.com", "Chris", "Hexton",
                "blogger", null, null, null));
        testEntries.add(createNewEntry("james@getvero.com", "James",
                "Lamont", null, null, "unsubscribed, dead-lead",
                "2012-12-31"));
        testEntries.add(createNewEntry("damien@getvero.com", "Damien", "Brzoska",
                null, null, "tshirt, warm-lead", "2013-06-15 00:00:01 +0000"));
        testEntries.add(createNewEntry("chris@getvero.com", "Chris", "Hexton",
                "developer", null, null, null));
        testEntries.forEach(simpleDBC::UpdateOrCreateEntries);
        assertFalse(simpleDBC.getMappingEntries().contains(testEntries.get(0)));
    }
}