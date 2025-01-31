import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    public static Item[] setupStore() {
        Item [] store = new Item[5];
        store [0] = new Item("Tv     ", 249.99);
        store [1] = new Item("Laptop", 1570.99);
        store [2] = new Item("Charger ", 29.99);
        store [3] = new Item("Speaker ", 99.99);
        store [4] = new Item("Mouse   ", 79.99);
        return store;
    }

    public static ArrayList<Item> createCart(String[] args, Item[] store) {
        ArrayList<Item> cart = new ArrayList<>();
        for (String arg : args) {
            try {
                int index = Integer.parseInt(arg);
                if (index >= 0 && index < store.length) {
                    cart.add(store[index]);
                } else {
                    System.out.println("The store does not have an item of index " + index + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("\"" + arg + "\" is not a valid integer.");
            }
        }
        return cart;
    }

    public static void printReceiptInOrder(ArrayList<Item> cart) {
        double total = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Receipt\n=========================\nItem             Price");
        for (Item item : cart) {
            total += item.getItemPrice();
            System.out.println(item.getItemName() + "         " + item.getItemPrice());
        }
        System.out.println("=========================\n(a) Subtotal:  " + df.format(total));
        System.out.println("(b) Sales Tax:  5%");
        System.out.println("(c) Total:  " + df.format(1.05*total));
        emptyCartReverseOrder(cart);
    }

    public static void emptyCartReverseOrder(ArrayList<Item> cart) {
        System.out.println("Removing all items from the cart in \"Last In First Out\" order.");
       while (cart.size() > 0) {
            System.out.println("Removing: " + cart.getLast().getItemName());
            cart.removeLast();
        }
        System.out.println("Cart has been emptied");
    }

    public static void main(String[] args) {
        Item[] store = setupStore();
        ArrayList<Item> cart = createCart(args, store);
        printReceiptInOrder(cart);
    }
}