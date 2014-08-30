package com.github.tellmp.test3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the DBConnector interface and provides the parsed
 * data inside of an list of entries ready to map to the database.
 * <p/>
 * Note: This class is a incomplete representation of the pre-mapping for
 * the DBConnector class. It is strongly recommended to implement this
 * depending to the ORM (Object-Relational Mapping).
 * <p/>
 * Created by Tell Mueller-Pettenpohl on 8/24/14.
 */
public class SimpleDBConnector implements DBConnector {

    private List<Entry> mappingEntries = new ArrayList<Entry>();

    public List<Entry> getMappingEntries() {
        return mappingEntries;
    }

    public void setMappingEntries(List<Entry> mappingEntries) {
        this.mappingEntries = mappingEntries;
    }

    /**
     * This method creates new entries for mapping purposes to the database.
     * The three first elements of the passing list of strings cannot be null.
     * <p/>
     * Note: This method is a incomplete representation of the pre-mapping for
     * the DBConnector class. It is strongly recommended to implement this
     * depending to the ORM (Object-Relational Mapping).
     *
     * @param parsedCSVData List of strings consisting the parsed data
     * @throws java.text.ParseException, IllegalArgumentException
     */
    public void mapCSVData(List<String> parsedCSVData) throws ParseException {
        Entry newEntry;

        switch (parsedCSVData.size()) {
            case 3:
                newEntry = Entry.createNewEntry(parsedCSVData.get(0),
                        parsedCSVData.get(1), parsedCSVData.get(2), null, null, null, null);
                break;
            case 4:
                newEntry = Entry.createNewEntry(parsedCSVData.get(0),
                        parsedCSVData.get(1), parsedCSVData.get(2), parsedCSVData.get(3), null, null, null);
                break;
            case 5:
                newEntry = Entry.createNewEntry(parsedCSVData.get(0),
                        parsedCSVData.get(1), parsedCSVData.get(2), null, null, parsedCSVData.get(3), parsedCSVData.get(4));
                break;
            case 6:
                newEntry = Entry.createNewEntry(parsedCSVData.get(0),
                        parsedCSVData.get(1), parsedCSVData.get(2), parsedCSVData.get(3), parsedCSVData.get(4), parsedCSVData.get(5), null);
                break;
            case 7:
                newEntry = Entry.createNewEntry(parsedCSVData.get(0),
                        parsedCSVData.get(1), parsedCSVData.get(2), parsedCSVData.get(3), parsedCSVData.get(4), parsedCSVData.get(5), parsedCSVData.get(6));
                break;
            default:
                throw new IllegalArgumentException("Wrong number of arguments: "
                        + parsedCSVData.size() + " - The first 3 elements " +
                        "cannot be null.");
        }
        UpdateOrCreateEntries(newEntry);
    }

    /**
     * This method updates an entry if its signature already exists in the
     * table or mapping list. Otherwise it will create a new entry in the
     * mapping list.
     *
     * @param newEntry to update or create in the mapping list
     */
    protected void UpdateOrCreateEntries(Entry newEntry) {
        for (Entry entry : mappingEntries) {
            if (newEntry.equalMappings(entry)) {
                mappingEntries.set(mappingEntries.indexOf(entry), newEntry);
                return;
            }
        }
        mappingEntries.add(newEntry);
    }

    @Override
    public String toString() {
        return "SimpleDBConnector{" +
                "mappingEntries=" + mappingEntries +
                '}';
    }
}
