package subjects.Сomporators;

import subjects.Ticket;
import java.util.Comparator;

public class ComparatorDiscount implements Comparator<Ticket> {
    /**
     * Compares two Ticket type objects based on their discount.
     * @param t1 The first Ticket object to compare.
     * @param t2 Second Ticket object to compare.
     * @return An int value that shows the result of the comparison
     */
    @Override
    public  int compare(Ticket t1, Ticket t2) {
        return (int) (t1.getDiscount()-t2.getDiscount());
    }

}
