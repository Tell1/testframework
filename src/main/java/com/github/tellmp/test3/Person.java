package com.github.tellmp.test3;

import java.util.UUID;

/**
 * This class represents an entry in the Person table in the database.
 * It is supposed to create instances of this representation by using the
 * createNewPerson method. This way it is certain that you create the new
 * instance of Person respecting the given rules of this table (i.e. NOT NULL
 * columns).
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class Person {
    private UUID id;
    private String email;
    private String first_name;
    private String last_name;

    protected Person(String email, String first_name, String last_name) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    /**
     * This method creates an instance of Person as the representation of the
     * row of the table Person. This way it is certain that you create the
     * new instance respecting the given constraints of this table (i.e. NOT
     * NULL columns). If the given parameters do not follow the constraints it
     * returns null.
     * Constraints: email, firstName and lastName are NOT NULL columns
     *
     * @param email     unique email of the user; NOT NULL
     * @param firstName first name of the user; NOT NULL
     * @param lastName  last name of the user; NOT NULL
     * @return new Person instance if parameters follow the constraints;
     * otherwise null
     */
    public static Person createNewPerson(String email, String firstName, String lastName) {
        if (email != null && !email.isEmpty() && firstName != null &&
                !firstName.isEmpty() &&
                lastName != null && !lastName.isEmpty()) {
            return new Person(email, firstName, lastName);
        }
        return null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!email.equals(person.email)) return false;
        if (!first_name.equals(person.first_name)) return false;
        if (!last_name.equals(person.last_name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + first_name.hashCode();
        result = 31 * result + last_name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }
}
