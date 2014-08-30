package com.github.tellmp.test3;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class TagTest {

    @Test
    public void testCreateNewTag() throws Exception {
        assertNull(Tag.createNewTag(null, null));

        Tag testTag1 = Tag.createNewTag("test1", "2012-12-31");
        assertEquals("test1", testTag1.getLastPurchase());
        LocalTime localTime = LocalTime.of(0, 0, 0);
        assertEquals(LocalDate.parse("2012-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)).atTime(localTime), testTag1.getTimestamp());

        Tag testTag2 = Tag.createNewTag("test2", "2013-06-15 00:00:01 +0000");
        assertEquals("test2", testTag2.getLastPurchase());
        assertEquals(LocalDateTime.parse("2013-06-15 00:00:01 +0000", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH)), testTag2.getTimestamp());
    }
}