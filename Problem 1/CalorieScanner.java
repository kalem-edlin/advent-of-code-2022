import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class CalorieScanner {
    public static void main(String[] args) {
        try {
            File file = new File("a.txt");
            Scanner sc = new Scanner(file);
            int greatestCount = findTopThreeTotalCalories(sc);
            System.out.println(greatestCount);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found with exception " + e);
        }
    }

    static private int findGreatestCalories(Scanner sc) {
        int maxCalories = 0;
        int current = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) {
                maxCalories = Math.max(maxCalories, current);
                current = 0;
            } else {
                current += Integer.valueOf(line);
            }
        }
        return maxCalories;
    }

    static private int findTopThreeTotalCalories(Scanner sc) {
        int current = 0;
        Queue<Integer> queue = new PriorityQueue<Integer>((i1, i2) -> i2 - i1);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("")) {
                queue.add(current);
                current = 0;
            } else {
                current += Integer.valueOf(line);
            }
        }
        int greatest = 0;
        for (int i = 0; i<3; i++) {
            greatest += queue.poll();
        }
        return greatest;
    }
}

