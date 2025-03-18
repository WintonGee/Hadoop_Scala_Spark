import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class StoreGenerator {

    // The "store" file should contain:
    // ID, storeName, address, city, ZIP, state, phoneNumber
    // Example record: 12, Best Buy, 123 Main St, SLO, 93401, CA, 805 555 5555

    int nextID = 0;
    ArrayList<String> storeNames = new ArrayList<>();
    ArrayList<String> addresses = new ArrayList<>();
    ArrayList<String> cities = new ArrayList<>();
    ArrayList<String> zips = new ArrayList<>();
    ArrayList<String> states = new ArrayList<>();
    ArrayList<String> phoneNumbers = new ArrayList<>();

    public void load() {
        // Load the store names
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/StoreNames"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                storeNames.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // Load the store locations
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/StoreLocations"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                addresses.add(values[0]);
                cities.add(values[1]);
                zips.add(values[2]);
                states.add(values[3]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Load the store phone numbers
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab1/src/StorePhoneNumbers"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                phoneNumbers.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Store get() {
        nextID++;
        String storeName = storeNames.get(new Random().nextInt(storeNames.size()));
        String address = addresses.get(new Random().nextInt(addresses.size()));
        String city = cities.get(new Random().nextInt(cities.size()));
        String zip = zips.get(new Random().nextInt(zips.size()));
        String state = states.get(new Random().nextInt(states.size()));
        String phoneNumber = phoneNumbers.get(new Random().nextInt(phoneNumbers.size()));
        return new Store(String.valueOf(nextID), storeName, address, city, zip, state, phoneNumber);
    }
}
