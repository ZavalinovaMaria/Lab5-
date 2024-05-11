package subjects;

import subjects.enams.TicketType;

import java.time.ZonedDateTime;

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

    public Ticket(Integer id,String name, Coordinates coordinates, ZonedDateTime creationDate, float price, double discount,Boolean refundable, TicketType type, Venue venue) {
        this.id = id;
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
    @Override
    public int compareTo(Ticket ticket ){
        return this.id.compareTo(ticket.id);}
    @Override
    public String toString(){
        return "Ticket{id=" +id+","+ ", name='" + name + '\'' + ", coordinates=" + coordinates +
                ", creationDate=" + creationDate + ", price=" + price +
                ", price='" + price + '\'' +
                ", discount=" + discount +
                ", type=" + type +
                ", venue=" + venue +
                '}';
    }
}

