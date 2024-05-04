package subjects;

import subjects.enams.VenueType;

public class Venue {

    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long capacity; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    public Venue( Integer id,String name,Long capacity,VenueType type){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VenueType getType() {
        return type;
    }

    public Long getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Ticket{id=" +id+","+ ", name='" + name + '\'' + ", capacity=" + capacity +
                ", type=" + type +
                '}';
    }
}


