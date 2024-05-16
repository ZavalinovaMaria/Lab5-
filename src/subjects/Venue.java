package subjects;

import subjects.enams.VenueType;

public class Venue {
    /**
     * A class representing venue.
     */
    private Integer id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long capacity; //Поле может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null

    /**
     *Creates a new venue instance.
     *
     * @param id        venue`s id
     * @param name   venue`s name
     * @param type venue`s type
     * @param capacity    venue`s capacity
     */

    public Venue( Integer id,String name,Long capacity,VenueType type){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.type = type;

    }
    public Venue(){}

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
        return "{ id=" +id+","+ '\n'+" name='" + name + '\'' + '\n'+" capacity=" + capacity +'\n'+
                " type=" + type +
                '}';
    }
}


