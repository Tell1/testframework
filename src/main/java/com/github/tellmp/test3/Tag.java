package com.github.tellmp.test3;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * This class represents an entry in the Tag table in the database.
 * It is supposed to create instances of this representation by using the
 * createNewTag method. This way it is certain that you create the new
 * instance of Person respecting the given rules of this table (i.e. NOT NULL
 * columns).
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class Tag {
    private String lastPurchase;
    private LocalDateTime timestamp;

    protected Tag(String lastPurchase, LocalDateTime timestamp) {
        this.lastPurchase = lastPurchase;
        this.timestamp = timestamp;
    }

    /**
     * This method creates an instance of PeopleData as the representation of
     * the row of the table PeopleData. This way it is certain that you
     * create the new instance respecting the given constraints of this table
     * (i.e. NOT NULL columns). If the given parameters do not follow the
     * constraints it returns null.
     *
     * @param lastPurchase last purchased product of the user
     * @param timestamp    when this entry was created
     * @return new Tag instance if parameters follow the constraints; otherwise
     * null
     * @throws ParseException if the given timestamp does not have the format:
     *                        yyyy-MM-dd or yyyy-MM-dd HH:mm:ss +0000
     */
    public static Tag createNewTag(String lastPurchase, String timestamp) throws ParseException {
        if (lastPurchase != null) {
            if (timestamp != null) {
                LocalDate localDate;
                LocalTime localTime = LocalTime.of(0, 0, 0);
                LocalDateTime localDateTime = LocalDateTime.of(0, 1, 1, 0, 0, 0);
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                if (timestamp.length() == "yyyy-MM-dd".length()) {
                    localDate = LocalDate.parse(timestamp, dtFormatter);
                    localDateTime = localDate.atTime(localTime);

                } else if (timestamp.length() == "yyyy-MM-dd HH:mm:ss +0000".length()) {
                    dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z", Locale.ENGLISH);
                    localDateTime = LocalDateTime.parse(timestamp, dtFormatter);
                }
                return new Tag(lastPurchase, localDateTime);
            }
            return new Tag(lastPurchase, null);
        }
        return null;
    }

    public String getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(String lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        if (lastPurchase != null ? !lastPurchase.equals(tag.lastPurchase) : tag.lastPurchase != null)
            return false;
        if (timestamp != null ? !timestamp.equals(tag.timestamp) : tag.timestamp != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = lastPurchase != null ? lastPurchase.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "lastPurchase='" + lastPurchase + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
