package com.github.tellmp.test3;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class PeopleDataTest {

    @Test
    public void testCreateNewPeopleData() throws Exception {
        assertNull(PeopleData.createNewPeopleData(null, null, null));
        assertNotNull(PeopleData.createNewPeopleData("testKey1", null, null).getId());
        PeopleData.createNewPeopleData("testKey1", "testValue1", UUID.randomUUID());
    }
}