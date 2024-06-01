package subjects.enams;

/**
 * Enum representing the ticket`s type.
 */
public enum TicketType {

    VIP("VIP"),
    USUAL("USUAL"),
    BUTGETARY("BUTGETARY"),
    CHEAP("CHEAP");
    public String name;
    TicketType(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
