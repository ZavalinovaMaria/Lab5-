package subjects.Сomporators;

import subjects.Ticket;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<Ticket> {
    @Override
    public  int compare(Ticket t1, Ticket t2) {
        return (int) (t1.getPrice()-t2.getPrice());
    }
}
