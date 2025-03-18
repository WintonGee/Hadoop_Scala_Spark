import java.io.*;
import java.util.*;

public class Main {

    public static final int DEFAULT_N = 10;

    // Example input in a line: 1,Laptop,1200.50
    // Expected output: 122, XBox, 230.23
    private static final String inputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab4/src/productinput";
    private static final String outputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab4/src/productoutput";

    // Note: Using a treemap is like hashmap, but the key values are sorted
    public static void main(String[] args) {
        ArrayList<String> list = getLineInputs();
        List<Record> records = getRecords(list);
        Collections.sort(records); // Sorts in desc based on price, works because of the compareTo method
        writeToFile(records);
    }

    public static ArrayList<String> getLineInputs() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Note: Assumes all inputs are in valid format
    public static List<Record> getRecords(ArrayList<String> list) {
        List<Record> records = new ArrayList<>();

        for (String s : list) {
            String[] tokens = s.split(",");
            // Provided Input: 1,Laptop,1200.50
            Record record = new Record(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
            records.add(record);
        }

        return records;
    }

    private static void writeToFile(List<Record> list) {
        int count = 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Record s : list) {
                writer.write(s.toString());
                writer.newLine();  // Add a newline after each entry
                count++;

                if (count >= DEFAULT_N)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}