package console;

import exceptions.NotUniqueValueException;
import subjects.Ticket;
import exceptions.*;
import subjects.enams.TicketType;

import java.util.*;
import java.time.format.DateTimeFormatter;

public class TicketCollection implements Checking {
    //класс коллекция продукта
    private Hashtable<Integer, Ticket> tickets;
    //тип дата инициализации размер
    public static KeyStorage keyStorage; ;


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
        keyStorage.addAll(tickets.keySet());
    }


    public void checkKeyUniqueness(Integer key) throws NotUniqueValueException{
        if(keyStorage.contains(key)) throw new NotUniqueValueException(String.format("Элемент с ключом со значением %s уже существует ", key));
        else keyStorage.add(key);
    }

    public void addNewKey(Integer key){
        try {
            checkKeyUniqueness(key);
        }
        catch (NotUniqueValueException e ){
            System.out.println(e.getMessage());
        }
    }


    public void deleteKey(Integer key){
        if(keyStorage.contains(key)) keyStorage.remove(key);
        else System.out.println("ключа нет такого ");

    }

    public Hashtable<Integer, Ticket> getCollection() {
        return tickets;
    }

    public void setTable(Hashtable<Integer, Ticket> tickets) {
        this.tickets = tickets;
        updateCharacteristic();
    }

    public void updateCharacteristic() {
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

    /*
    public void setType(TicketType type) {
        this.lastOrganizationTypeWorkedWith = lastOrganizationTypeWorkedWith;
    }

     */






    public int getCountOfElements() {
        return countOfElements;
    }







    /**
      * Добавляет новый продукт в коллекцию
     */
    public void addTicket(Integer key,Ticket ticket){

        tickets.put(key,ticket);
        //нужно чтобы человек вводил уникальный номер //если номер уже есть выпадает исключение  и пишет
        // containsKey(Object key) если true т выкидываем исключение
        // уже есть такой номер пожалйста введите элемент с не таким номером --вывод всех ключей(id) которые уж есть
    }
    /**
     * Обновляет продукт по его id
     * @param productId id продукта
     * @param updatedTicket новый продукт
     */

}
