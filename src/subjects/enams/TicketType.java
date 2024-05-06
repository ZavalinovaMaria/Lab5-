package subjects.enams;

public enum TicketType {
    VIP("vip"),
    USUAL("usual"),
    BUTGETARY("budgetary"),
    CHEAP("cheap");
    public String name;
    TicketType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return "=" +name +
                '}';
    }
}
