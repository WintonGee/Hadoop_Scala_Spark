import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class JavaProgram {

    // Example input in a line: 2017, 1, 1, 6:23:11, 43
    // Expected output: 2017/1/1 6:23:11 43, 6:35:10 59
    private static final String inputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab3/src/salesinput";
    private static final String outputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab3/src/salesoutput";

    // Note: Using a treemap is like hashmap, but the key values are sorted
    public static void main(String[] args) {
        ArrayList<String> list = getLineInputs();
        TreeMap<String, String> map = calculate(list);
        writeToFile(map);
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
    public static TreeMap<String, String> calculate(ArrayList<String> list) {
        TreeMap<String, String> hashMap = new TreeMap<>();

        for (String s : list) {
            String[] tokens = s.split(",");
            // Provided Input: 1,2023/04/21,23:51:47,2,2
            String key = tokens[1];
            String currentString = hashMap.getOrDefault(key, "");
            String newString = currentString + ", " + tokens[2] + " " + tokens[0];
            hashMap.put(key, newString);
        }

        return hashMap;
    }

    private static void writeToFile(TreeMap<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                writer.write(key + " " + value);
                writer.newLine();  // Add a newline after each entry
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}