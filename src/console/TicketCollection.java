package console;

import exceptions.NotExistingValueException;
import subjects.Ticket;
import java.util.*;

public class TicketCollection implements Checking  {
    /**
     * A class representing a collection of tickets.
     */
    private Hashtable<Integer, Ticket> tickets;
    private Date initializationDate;
    private String type;
    private int countOfElements;
    private String internalFileType;

    public TicketCollection(Hashtable<Integer, Ticket> tickets) {
        this.tickets = tickets;
        type = tickets.getClass().getSimpleName();
        internalFileType = "Ticket";
        countOfElements = tickets.size();
        initializationDate = new Date();
        for (Ticket ticket : tickets.values()){
            addNewVenueId(ticket.getVenue().getId());
        }
        for(Integer key:tickets.keySet()){
            addNewKey(key);
        }
    }
    public Hashtable<Integer, Ticket> getCollection() {
        return tickets;
    }

    /**
     * The method updates the number of elements in the collection and sets the current date as the collection's initialization date.
     */
    public void updateData() {
        countOfElements = tickets.size();
        initializationDate = new Date();
    }
    @Override
    public String toString() {
        return  "\n1. Initialization date: " + initializationDate +
                "\n2. Collection type: " + type +
                "\n3. Internal files type: " + internalFileType +
                "\n4. Amount of elements: " + countOfElements;
    }
    public String getType() {
        return type;
    }
    public int getCountOfElements() {
        return countOfElements;
    }

    /**
     * The method checks the presence of an element in the collection using the specified key.
     * If an element with such a key exists, it is removed from the collection, else an error message is displayed.
     * @param key The key of the element to remove from the collection.
     */

    public void deleteKey(Integer key){
        try {
            checkExistence(key);
        }
        catch (NotExistingValueException e){
            System.out.println(e.getMessage());
        }
    }
}
