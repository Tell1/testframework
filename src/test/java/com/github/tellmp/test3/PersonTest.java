package com.github.tellmp.test3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class PersonTest {

    @Test
    public void testCreateNewPerson() throws Exception {
        assertNull(Person.createNewPerson(null, null, null));
        assertNull(Person.createNewPerson("chris@getvero.com", null, null));
        assertNull(Person.createNewPerson("chris@getvero.com", "Chris", null));
        assertNotNull(new Person("chris@getvero.com", "Chris",
                "Hexton").getId());
        assertSame(new Person("chris@getvero.com", "Chris", "Hexton").getEmail(), Person.createNewPerson("chris@getvero.com", "Chris", "Hexton").getEmail());
        assertSame(new Person("chris@getvero.com", "Chris", "Hexton").getFirst_name(), Person.createNewPerson("chris@getvero.com", "Chris", "Hexton").getFirst_name());
        assertSame(new Person("chris@getvero.com", "Chris", "Hexton").getLast_name(), Person.createNewPerson("chris@getvero.com", "Chris", "Hexton").getLast_name());
    }
}