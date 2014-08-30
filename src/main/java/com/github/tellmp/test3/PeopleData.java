package com.github.tellmp.test3;

import java.util.UUID;

/**
 * This class represents an entry in the PeopleData table in the database.
 * It is supposed to create instances of this representation by using the
 * createNewPeopleData method. This way it is certain that you create the new
 * instance of Person respecting the given rules of this table (i.e. NOT NULL
 * columns).
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class PeopleData {

    private UUID id;
    private String key;
    private String value;
    private UUID person_id;

    protected PeopleData(String key, String value, UUID person_id) {
        this.id = UUID.randomUUID();
        this.key = key;
        this.value = value;
        this.person_id = person_id;
    }

    /**
     * This method creates an instance of PeopleData as the representation of
     * the row of the table PeopleData. This way it is certain that you
     * create the new instance respecting the given constraints of this table
     * (i.e. NOT NULL columns). If the given parameters do not follow the
     * constraints it returns null.
     *
     * @param key   key values of the user; can be a sequence of strings
     * @param value values of the user; can be a sequence of strings
     * @return new PeopleData instance if parameters follow the constraints;
     * otherwise null
     */
    public static PeopleData createNewPeopleData(String key, String value, UUID person_id) {
        if (key != null && value != null && person_id != null) {
            return new PeopleData(key, value, person_id);
        } else if (key != null) {
            return new PeopleData(key, null, person_id);
        } else if (value != null) {
            return new PeopleData(null, value, person_id);
        } else if (person_id != null) {
            return new PeopleData(null, null, person_id);
        }
        return null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getPerson_id() {
        return person_id;
    }

    public void setPerson_id(UUID person_id) {
        this.person_id = person_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleData)) return false;

        PeopleData that = (PeopleData) o;

        if (key != null ? !key.equals(that.key) : that.key != null)
            return false;
        if (value != null ? !value.equals(that.value) : that.value != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (person_id != null ? person_id.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PeopleData{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", person_id=" + person_id +
                '}';
    }
}
