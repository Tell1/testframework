package com.github.tellmp.test3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class EntryTest {

    @Test
    public void testCreateNewNullEntry() throws Exception {
        assertNull(Entry.createNewEntry(null, null, null, null, null, null, null));
        assertNull(Entry.createNewEntry("chris@getvero.com", null, null, null, null, null, null));
        assertNull(Entry.createNewEntry("chris@getvero.com", "Chris", null, null, null, null, null));
        assertNotNull(Entry.createNewEntry("chris@getvero.com", "Chris",
                "Hexton", null, null, null, null));
    }

    @Test
    public void testCreateNewEntry() throws Exception {
        List<String> peopleDataList = new ArrayList<String>();
        List<String> tagList = new ArrayList<String>();
        peopleDataList.add("unsubscribed, dead-lead");
        peopleDataList.add("testKey");
        tagList.add("testStatus");
        tagList.add("2012-12-31");
        Entry entry = Entry.createNewEntry("chris@getvero.com", "Chris", "Hexton", peopleDataList.get(0), peopleDataList.get(1), tagList.get(0), tagList.get(1));
        assertEquals("chris@getvero.com", entry.getPerson().getEmail());
        assertEquals(peopleDataList.get(0), entry.getPeopleData().getKey());
        assertEquals(peopleDataList.get(1), entry.getPeopleData().getValue());
        assertEquals(tagList.get(0), entry.getTag().getLastPurchase());
        assertEquals(tagList.get(1).concat("T00:00"), entry.getTag().getTimestamp().toString());
    }
}