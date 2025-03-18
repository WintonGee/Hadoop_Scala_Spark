import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SalesGenerator {

    int nextId = 0;
    int storeIndex = 0;
    int customerIndex = 0;

    public Sales get() {
        nextId++;
        storeIndex++;
        customerIndex++;

        String date = generateRandomDate();
        String time = generateRandomTime();

        String storeID = Main.storeList.get(storeIndex % Main.storeList.size()).ID;
        String customerID = Main.customerList.get(customerIndex % Main.customerList.size()).ID;

        return new Sales(String.valueOf(nextId), date, time, storeID, customerID);
    }

    // This should read in the "customer" file data
    // Generates: Sales information

    // ID, date, time, storeID, customerID
    // Example record: 13, 2017/01/01, 13:23:11, 23, 56

    private static String generateRandomDate() {
        LocalDateTime randomDateTime = LocalDateTime.now().minusDays(new Random().nextInt(365));
        return randomDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    private static String generateRandomTime() {
        LocalDateTime randomDateTime = LocalDateTime.now()
                .minusHours(new Random().nextInt(24))
                .minusMinutes(new Random().nextInt(60))
                .minusSeconds(new Random().nextInt(60));
        return randomDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

}
