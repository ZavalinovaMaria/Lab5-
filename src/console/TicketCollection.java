package console;

import exceptions.NotUniqueValueException;
import exceptions.NotExistingValueException;

import subjects.Ticket;
//import exceptions.*;

import java.util.*;


public class TicketCollection implements Checking  {
    //класс коллекция продукта
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
        for(Integer key:tickets.keySet()){
            addNewKey(key);
        };
    }



    public Hashtable<Integer, Ticket> getCollection() {
        return tickets;
    }

    public void setTable(Hashtable<Integer, Ticket> tickets) {
        this.tickets = tickets;
        updateData();
    }

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
    public void deleteKey(Integer key){
        try {
            checkExistence(key);
        }
        catch (NotExistingValueException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Добавляет новый продукт в коллекцию
     */

    @Override
    public void addNewKey(Integer key){
        try{
            checkUniqueness(key);
        }
        catch (NotUniqueValueException e){
            System.out.println(e.getMessage());
        }
    }



    /*public void addTicket(Integer key,Ticket ticket)  {
        try{
            if(!checkingUniqueness(key)){
                tickets.put(key, ticket);
                setTable(tickets);}
            else{
                System.out.println("Билет нелья добаввить в коллекцию");}}
        catch(NotUniqueValueException e){
            System.out.println(" ");}}

     */

    public void deleteTicket(Integer key,Ticket ticket){
        deleteKey(key);
        tickets.remove(key,ticket);
        setTable(tickets);
        updateData();
    }


}