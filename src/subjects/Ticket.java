package subjects;

import subjects.enams.TicketType;
import java.time.ZonedDateTime;

/**
 * A class representing ticket.
 */
public  class Ticket implements Comparable<Ticket> {

    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private static int lastId = 0;
    String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime  creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private double discount; //Поле может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable;
    private TicketType type; //Поле не может быть null
    private Venue venue; //Поле не может быть null
    private User user;

    /**
     *Creates a new ticket instance.
     *
     * @param id        ticket`s id
     * @param name   ticket`s name
     * @param coordinates    ticket`s coordinates
     * @param creationDate   ticket`s creation date
     * @param price ticket`s price
     * @param discount    ticket`s discount
     * @param refundable   ticket`s refundable
     * @param type ticket`s type
     * @param venue    ticket`s venue
     */

    public Ticket(Integer id,User user,String name, Coordinates coordinates, ZonedDateTime creationDate, float price, double discount,Boolean refundable, TicketType type, Venue venue) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
    }
    public Ticket(){}
    public User getUser(){
        return user;
    }
    public void setUser(User user){
        this.user = user;
    }



    public String getName() {
        return name;}
    public java.time.ZonedDateTime getCreationDate() {
        return creationDate;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Coordinates getCoordinates() {
        return coordinates;}
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    public float getPrice() {
        return price;}
    public void setPrice(float price) {
        this.price = price;
    }
    public double getDiscount() {
        return discount;}
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public TicketType getType() {
        return type;
    }

    public Venue getVenue() {
        return venue;
    }

    public Boolean getRefundable() {
        return refundable;}

    public void setRefundable(Boolean refundable) {
        this.refundable = refundable;
    }
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    public void setType(TicketType type) {
        this.type = type;
    }

   public void setCharacteristic(Integer id,User user,String name, Coordinates coordinates, ZonedDateTime creationDate, float price, double discount,Boolean refundable, TicketType type, Venue venue){
        this.setId(id);
        this.setUser(user);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate(creationDate);
        this.setPrice(price);
        this.setDiscount(discount);
        this.setRefundable(refundable);
        this.setType(type);
        this.setVenue(venue);
   }

    @Override
    public int compareTo(Ticket ticket ){
        return this.id.compareTo(ticket.id);}
    @Override
    public String toString(){
        return "Ticket{ id=" +id+'\n'+ " name='" + name + '\'' +'\n' + " coordinates=" + coordinates + '\n' +
                " creationDate=" + creationDate + '\n'+
                " price='" + price + '\'' +'\n'+
                " discount=" + discount +'\n'+
                " type=" + type +'\n'+
                " venue=" + venue +'\n'+
                '}';
    }
}

