import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // These public static variables will be used to hold information when needing to cross-reference data
    public static List<Store> storeList = new ArrayList<>();
    public static List<Customer> customerList = new ArrayList<>();
    public static List<Sales> salesList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<LineItem> lineItemList = new ArrayList<>();

    public static void main(String[] args) {
        createStore();
        createCustomer();
        createSales();
        createProduct();
        createLineItem();
    }

    // Notes: Use a hashmap to keep track of the id's

    // Task 1:
    // Create 100 records with random data in this file.

    // The "store" file should contain:
    // ID, storeName, address, city, ZIP, state, phoneNumber
    // Example record: 12, Best Buy, 123 Main St, SLO, 93401, CA, 805 555 5555
    public static void createStore() {
        StoreGenerator storeGenerator = new StoreGenerator();
        storeGenerator.load();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("store"))) {
            for (int i = 0; i < 100; i++) {
                Store store = storeGenerator.get();
                storeList.add(store);
                writer.write(store.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 2:
    // Create 1,000 records with random data in the file

    // The "customer" file should contain:
    // ID, name, birth date, address, city, ZIP, state, phoneNumber
    // Example record: 12, John Sam, 1989/11/11, 123 Main St, SLO, 93401, CA, 805 555 5555
    public static void createCustomer() {
        CustomerGenerator customerGenerator = new CustomerGenerator();
        customerGenerator.load();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("customer"))) {
            for (int i = 0; i < 1000; i++) {
                Customer customer = customerGenerator.get();
                customerList.add(customer);
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 3:
    // Create 2,000 records with random data in the file.
    // Make sure you select store IDs and customer IDs from existing store and customer IDs, respectively.
    // There should be at least one sale for every customer and every store.

    // The "sales" file should contain:
    // ID, date, time, storeID, customerID
    // Example record: 13, 2017/01/01, 13:23:11, 23, 56
    public static void createSales() {
        SalesGenerator salesGenerator = new SalesGenerator();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("sales"))) {
            for (int i = 0; i < 2000; i++) {
                Sales sales = salesGenerator.get();
                salesList.add(sales);
                writer.write(sales.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 4:
    // Create 100 products.

    // The "product" file should contain:
    // ID, description, price
    // For example: 122, XBox 360, 230.23
    public static void createProduct() {
        ProductGenerator productGenerator = new ProductGenerator();
        productGenerator.load();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("product"))) {
            for (int i = 0; i < 100; i++) {
                Product product = productGenerator.get();
                productList.add(product);
                writer.write(product.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Task 5:
    // Create 4,000 lineItem records.
    // Make sure that ever line item references an existing sales and product.
    // Every sale consists of one or more line items.

    // The "lineItem" file should contain:
    // ID, salesID, productID, quantity.
    // For example: 11, 13, 122, 8.
    public static void createLineItem() {
        LineItemGenerator lineItemGenerator = new LineItemGenerator();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lineItem"))) {
            for (int i = 0; i < 4000; i++) {
                LineItem lineItem = lineItemGenerator.get();
                lineItemList.add(lineItem);
                writer.write(lineItem.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}