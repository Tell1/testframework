package com.github.tellmp.test3;

import java.text.ParseException;
import java.util.UUID;

import static com.github.tellmp.test3.PeopleData.createNewPeopleData;
import static com.github.tellmp.test3.Person.createNewPerson;
import static com.github.tellmp.test3.Tag.createNewTag;

/**
 * This class represents an entry of the Person, PeopleData and Tags in the
 * database.It is supposed to create instances of this representation by using the
 * createNewEntry method. This way it is certain that you create the new
 * instance of Entry respecting the given rules of all the tables (i.e. NOT NULL
 * columns).
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class Entry {

    private Person person;
    private PeopleData peopleData;
    private Tag tag;

    protected Entry(Person person, PeopleData peopleData, Tag tag) {
        this.person = person;
        this.peopleData = peopleData;
        this.tag = tag;
    }

    /**
     * This method creates an instance of Entry as the representation of the
     * row of the database tables. This way it is certain that you create the
     * new instance respecting the given constraints of this table (i.e. NOT
     * NULL columns). If the given parameters do not follow the constraints it
     * returns null.
     * Constraints: email, firstName and lastName are NOT NULL columns
     *
     * @param email        unique email of the user; NOT NULL
     * @param firstName    first name of the user; NOT NULL
     * @param lastName     last name of the user; NOT NULL
     * @param key          key values of the user; can be a sequence of strings
     * @param value        values of the user; can be a sequence of strings
     * @param lastPurchase last purchased product of the user
     * @param timestamp    when this entry was created
     * @return new Entry if parameters follow the constraints; otherwise null
     * @throws ParseException if the given timestamp does not have the format:
     *                        yyyy-MM-dd or yyyy-MM-dd HH:mm:ss +0000
     */
    public static Entry createNewEntry(String email, String firstName, String lastName, String key, String value, String lastPurchase, String timestamp) throws ParseException {
        if (email != null && firstName != null && lastName != null) {
            return new Entry(createNewPerson(email, firstName, lastName), createNewPeopleData(key, value, UUID.randomUUID()), createNewTag(lastPurchase, timestamp));
        }
        return null;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PeopleData getPeopleData() {
        return peopleData;
    }

    public void setPeopleData(PeopleData peopleData) {
        this.peopleData = peopleData;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    /**
     * This method checks if two entries are equals in terms of the
     * identifiers of the table. Use this method to determine if to rows in
     * the table of the same user.
     *
     * @param o given entry to compare with this entry
     * @return true if this entry already exists in the table; otherwise false
     */
    public boolean equalMappings(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;

        if (!person.equals(entry.person)) return false;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;

        Entry entry = (Entry) o;
        if (peopleData == null && entry.peopleData == null) return true;
        if (peopleData != null ? !peopleData.equals(entry.peopleData) : entry.peopleData != null)
            return false;
        if (!person.equals(entry.person)) return false;
        if (tag == null && entry.tag == null) return true;
        if (tag != null ? !tag.equals(entry.tag) : entry.tag != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = person.hashCode();
        result = 31 * result + (peopleData != null ? peopleData.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "person=" + person +
                ", peopleData=" + peopleData +
                ", tag=" + tag +
                '}';
    }
}

