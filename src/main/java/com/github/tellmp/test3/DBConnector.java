package com.github.tellmp.test3;


import java.text.ParseException;
import java.util.List;

/**
 * This interface is the representation of a database connector.
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public interface DBConnector {

    /**
     * This method creates new entries for mapping purposes to the database.
     * It is strongly recommended to implement this depending to the
     * ORM (Object-Relational Mapping).
     *
     * @param parsedCSVData List of strings consisting the parsed data
     * @throws java.text.ParseException, IllegalArgumentException
     */
    public void mapCSVData(List<String> parsedCSVData) throws ParseException;
}
