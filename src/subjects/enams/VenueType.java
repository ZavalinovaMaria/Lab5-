package subjects.enams;

public enum VenueType {
    BAR("bar"),
    CINEMA("cinema"),
    MALL("mall"),
    STADIUM("stadium");
    public String name;
    VenueType(String name){
        this.name= name;
    }

}
