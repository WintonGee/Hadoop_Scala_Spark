import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class JavaProgram {

    private static final String inputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab2/src/inputs";
    private static final String outputPath = "/Users/wintongee/Desktop/School/Winter 2024/CSC369/Repos/Lab2/src/outputs";

    // Note: Using a treemap is like hashmap, but the key values are sorted
    public static void main(String[] args) {
        ArrayList<String> list = getLineInputs();
        TreeMap<String, Integer> map = calculate(list);
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
    // Example string input: "2024/01/12 60"
    // This will return a hashmap with -> Key: Date, Value: Number of sales
    public static TreeMap<String, Integer> calculate(ArrayList<String> list) {
        TreeMap<String, Integer> hashMap = new TreeMap<>();

        for (String s : list) {
            String[] tokens = s.split(",");
            int currentAmount = hashMap.getOrDefault(tokens[1], 0);
            int newSum = currentAmount + 1;
            hashMap.put(tokens[1], newSum);
        }

        return hashMap;
    }

    private static void writeToFile(TreeMap<String, Integer> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String key : map.keySet()) {
                int value = map.get(key);
                writer.write(key + " " + value);
                writer.newLine();  // Add a newline after each entry
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}