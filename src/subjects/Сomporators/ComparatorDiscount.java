package subjects.Сomporators;

import subjects.Ticket;

import java.util.Comparator;

public class ComparatorDiscount implements Comparator<Ticket> {
    @Override
    public  int compare(Ticket t1, Ticket t2) {
        return (int) (t1.getDiscount()-t2.getDiscount());
    }

}
