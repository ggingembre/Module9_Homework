import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.reverseOrder;

/**
 * Created by Guillaume Gingembre on 05/04/2017.
 */
public final class DataManagement {

    /*
    This is a utility class where I will write methods to process data (sort, filter, group, etc.)
    As a result, the constructor should be private, and all methods in it should be static
     */

    private DataManagement() {}

    public static void sortingByPrice(List<Order> orders){
        Collections.sort(orders, (Order o1, Order o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));
        //orders
          //      .stream()
            //    .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));
    }

    public static void sortByPriceInDecreaseOrder ( List <Order> orders ) {
        orders.sort ( Comparator.comparing ( Order::getPrice, reverseOrder ( ) ) );
    }


}
