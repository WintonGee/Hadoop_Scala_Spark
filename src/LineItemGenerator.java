import java.util.Random;

public class LineItemGenerator {

    int nextID = 0;

    int salesIndex = 0;
    int productIndex = 0;

    public LineItem get() {
        nextID++;
        salesIndex++;
        productIndex++;

        String salesID = Main.salesList.get(salesIndex % Main.salesList.size()).ID;
        String productID = Main.productList.get(productIndex % Main.productList.size()).ID;

        return new LineItem(String.valueOf(nextID), salesID, productID, String.valueOf(new Random().nextInt(50)));
    }

    // Note: This should read in the "store" and "product" file data

    // ID, salesID, productID, quantity.
    // For example: 11, 13, 122, 8.

}
