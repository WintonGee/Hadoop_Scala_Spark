public class Product {

    // ID, description, price
    // For example: 122, XBox 360, 230.23

    String ID, description, price;

    public Product(String ID, String description, String price) {
        this.ID = ID;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return ID + "," + description + "," + price;
    }

}
