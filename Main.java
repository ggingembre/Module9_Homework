import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Guillaume Gingembre on 04/04/2017.
 */
public class Main {

    public static void main(String[] args) {

        // В Main создайте 10 заказов с 10 пользователями и добавьте его в List.
        // public Order(long id, int price, Currency currency, String itemName, String shopIdentificator, User user)
        // public User(long id, String firstName, String lastName, String city, int balance)

        User guillaume = new User (111, "Guillaume", "Gingembre", "Paris", 1000);
        User elena = new User (112, "Elena", "Izotova", "Pologi", 500);
        User charles = new User (113, "Charles", "Galant", "Kiev", 900);
        User xsenia = new User (114, "Xsenia", "Galant", "Donetsk", 400);
        User jf = new User (115, "Jean François", "Drean","Lyon", 1200);
        User larissa = new User (116, "Larissa", "Drean", "Poltova", 500);
        User vassia = new User (117, "Vassia", "Kuznetsov", "Kiev", 1100);
        User natasha = new User (118, "Natasha", "Kuznetsova", "Donetsk", 1000);
        User franck = new User (119, "Franck", "Mgumbo", "Clichy", 300);
        User lisa = new User (120, "Lisa", "Izotova", "Pologi", 100);
        User igor = new User(130, "Igor", "Petrov","Kiev",10000);

        Order order1 = new Order (1, 1000, Currency.getInstance("EUR"), "Lenovo ThinkPad", "Lenovo.com", guillaume);
        Order order2 = new Order (2,500,Currency.getInstance("EUR"),"Lenovo IdeaPad", "Tsum", elena);
        Order order3 = new Order (3, 1500, Currency.getInstance("USD"),"Gaming Monster", "Rozetka", charles );
        Order order4 = new Order (4,300, Currency.getInstance("UAH"),"Honey", "Izotov", xsenia);
        Order order5 = new Order (5, 30000, Currency.getInstance("EUR"), "Subaru wrx", "La Centrale", jf);
        Order order6 = new Order (6,2000, Currency.getInstance("USD"), "Trip to Japan", "Oskar",larissa);
        Order order7 = new Order (7, 50,Currency.getInstance("USD"),"Single Malt Whisky", "Airport CDG", vassia);
        Order order8 = new Order (8, 450, Currency.getInstance("UAH"), "Honey", "Izotov", natasha);
        Order order9 = new Order (9, 5000, Currency.getInstance("USD"), "Kamaz", "AvtoRinok", franck);
        Order order10 = new Order (10, 2000, Currency.getInstance("UAH"), "Lego Friends", "Lego Lviv", lisa);

        // putting all orders in a list

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);
        orderList.add(order6);
        orderList.add(order7);
        orderList.add(order8);
        orderList.add(order9);
        orderList.add(order10);

        // checking the list before sorting

        orderList.forEach(o -> System.out.println(o));

        // sorting by price

        System.out.println("\nPrice sorting, descending order:");

        orderList
                .stream()
                .sorted((o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()))
                .forEach(o -> System.out.println(o));

        // sorting by town
        System.out.println("\nTown sorting, ascending order:");
        orderList
                .stream()
                .sorted((o1, o2) -> (o1.getUser().getCity()).compareTo(o2.getUser().getCity()))
                .forEach(o -> System.out.println(o));


        // sorting by town and price
        System.out.println("\nPrice sorting, descending order, then city sorting, ascending order:");
        Comparator<Order> byCity = (o1, o2) -> (o1.getUser().getCity()).compareTo(o2.getUser().getCity());
        Comparator<Order> byPrice = (o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice());

        orderList.stream().sorted(byPrice.thenComparing(byCity))
                .forEach(o -> System.out.println(o));

        // sorting by itemName, id and city
        System.out.println("\nSorting by item name, ID, then city:");
        Comparator<Order> byId = (o1, o2) -> Long.compare((o1.getId()),(o2.getId()));
        Comparator<Order> byItemName = (o1, o2) -> (o1.getItemName()).compareTo(o2.getItemName());

        orderList.stream().sorted(byItemName.thenComparing(byId).thenComparing(byCity))
                .forEach(o -> System.out.println(o));

        /*
        удалите дублированные данные со списка
        удалите объекты, где цена меньше 1500
        разделите список на 2 списка - заказы в долларах и в гривнах
        разделите список на столько списков, сколько уникальных городов в User
         */

        List <Order> orderListDuplicate = new ArrayList<>();
        orderListDuplicate.addAll(orderList);
        orderListDuplicate.addAll(orderList);

        System.out.println("\nWe see that all items are duplicated: ");
        orderListDuplicate.forEach(o -> System.out.println(o));

        List<Order> noduplicates = orderListDuplicate.stream().distinct().collect(Collectors.toList());

        System.out.println("\nThis is the duplicatelist cleaned from duplicates:");
        noduplicates.forEach(o -> System.out.println(o));

        // delete objects where price below 1500

        noduplicates.removeIf(o -> o.getPrice()<1500);

        System.out.println("\nWe have erased from the list items whose price is below 1500");
        noduplicates.forEach(o -> System.out.println(o));

        // create two lists: one for orders in USD and one for orders in UAH

        List<Order> ordersUAH = orderList.stream().filter(o -> o.getCurrency()== Currency.getInstance("UAH"))
                .collect(Collectors.toList());

        System.out.println("\nHere are orders denominated in UAH: ");
        ordersUAH.forEach(o -> System.out.println(o));

        List<Order> ordersUSD = orderList.stream().filter(o -> o.getCurrency()== Currency.getInstance("USD"))
                .collect(Collectors.toList());

        System.out.println("\nHere are orders denominated in USD: ");
        ordersUSD.forEach(o -> System.out.println(o));


        // разделите список на столько списков, сколько уникальных городов в User
        Map<String, List<Order>> byUserCity
                = orderList.stream()
                .collect(Collectors.groupingBy(order -> order.getUser ().getCity ()));

        byUserCity.forEach ( ( city, order ) -> System.out.println ( city + ": " + order) );

        /*
        -проверьте, содержит ли сет заказ, где фамилия пользователя - “Petrov”
        -удалите заказы в USD
         */

        // creating orders with petrov to check my code
        Order order11 = new Order (11, 200, Currency.getInstance("UAH"), "Wine", "Cilpo", igor);
        Order order12 = new Order (12, 200, Currency.getInstance("UAH"), "Kalbasa", "Cilpo", igor);

        orderList.add(order11);
        orderList.add(order12);

        if (orderList.stream().anyMatch(o -> o.getUser().getLastName().equalsIgnoreCase("Petrov"))){
            System.out.println("The following are orders from users with family name Petrov:");
            orderList.stream()
                    .filter(o -> o.getUser().getLastName().equalsIgnoreCase("Petrov"))
                    .forEach(o-> System.out.println(o));
        } else System.out.println("There is no order from user with family name Petrov");


        System.out.println("We have erased orders denominated in USD:");
        orderList.removeIf(order -> order.getCurrency()== Currency.getInstance("USD"));
        orderList.forEach(order -> System.out.println(order));

    }

}
