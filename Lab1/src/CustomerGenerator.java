import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CustomerGenerator {

    int nextId = 0;
    int storeIndex = 0; // Used so that it can iterate through all stores

    ArrayList<String> customerNames = new ArrayList<>();
    ArrayList<String> birthdays = new ArrayList<>();

    public void load() {
        // Load the customer names
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/CustomerNames"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customerNames.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Load the birthdays
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/CustomerBirthdays"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                birthdays.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer get() {
        Store store = Main.storeList.get(storeIndex % Main.storeList.size());
        storeIndex++;

        nextId++;
        String name = customerNames.get(new Random().nextInt(customerNames.size()));
        String birthdate = birthdays.get(new Random().nextInt(birthdays.size()));
        String address = store.address;
        String city = store.city;
        String zip = store.ZIP;
        String state = store.state;
        String phoneNumber = store.phoneNumber;
        return new Customer(String.valueOf(nextId), name, birthdate, address, city, zip, state, phoneNumber);
    }

    // Note: This should read in the "store" file data
    // Generates: Customer information

    // ID, name, birth date, address, city, ZIP, state, phoneNumber
    // Example record: 12, John Sam, 1989/11/11, 123 Main St, SLO, 93401, CA, 805 555 5555


}
