// Task23_FilterSortItems.java
import java.util.*;

public class Task23_FilterSortItems {

    static class Item {
        String name;
        double rating;
        Item(String name, double rating) {
            this.name = name;
            this.rating = rating;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            items.add(new Item(sc.next(), sc.nextDouble()));
        }
        double threshold = sc.nextDouble();

        // Filter items below threshold
        items.removeIf(item -> item.rating < threshold);

        // Sort: descending rating, then alphabetical name on tie
        items.sort((a, b) -> {
            int cmp = Double.compare(b.rating, a.rating);
            return cmp != 0 ? cmp : a.name.compareTo(b.name);
        });

        for (Item item : items) {
            System.out.println(item.name + " " + item.rating);
        }
    }
}
