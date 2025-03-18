public class LineItem {

    // ID, salesID, productID, quantity.
    // For example: 11, 13, 122, 8.

    String ID, salesID, productID, quantity;

    public LineItem(String ID, String salesID, String productID, String quantity) {
        this.ID = ID;
        this.salesID = salesID;
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ID + "," + salesID + "," + productID + "," + quantity;
    }

}
