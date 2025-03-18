import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class ProductGenerator {

    int nextID = 0;

    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/ProductData"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                descriptions.add(values[0]);
                prices.add(values[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Product get() {
        nextID++;

        String description = descriptions.get(new Random().nextInt(descriptions.size()));
        String price = prices.get(new Random().nextInt(prices.size()));

        return new Product(String.valueOf(nextID),description,price);
    }

    // ID, description, price
    // For example: 122, XBox 360, 230.23

}
