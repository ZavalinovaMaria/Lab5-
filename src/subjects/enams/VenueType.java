package subjects.enams;

public enum VenueType {
    /**
     * Enum representing the venue`s type.
     */
    BAR("BAR"),
    CINEMA("CINEMA"),
    MALL("MALL"),
    STADIUM("STADIUM");
    public String name;
    VenueType(String name){
        this.name= name;
    }
    @Override
    public String toString(){
        return name;
    }


}
