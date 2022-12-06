import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

class Problem4 {
    public static void main(String args []) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            int overlappedPairs = findOverlappedPairs(sc);
            System.out.println(overlappedPairs);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found with exception " + e);
        }
    }

    private static int findContainedPairs(Scanner sc) {
        int pairs = 0;
        while (sc.hasNextLine()) {
            String[] assignmentRanges = sc.nextLine().split(",");

            Integer[] first = Stream.of(assignmentRanges[0].split("-")).map(Integer::valueOf).toArray(Integer[]::new);
            Integer[] second = Stream.of(assignmentRanges[1].split("-")).map(Integer::valueOf).toArray(Integer[]::new);

            if (isContained(first, second) || isContained(second, first)) {
                pairs++;
            }
        }
        return pairs;
    }

    private static int findOverlappedPairs(Scanner sc) {
        int pairs = 0;
        while (sc.hasNextLine()) {
            String[] assignmentRanges = sc.nextLine().split(",");

            Integer[] first = Stream.of(assignmentRanges[0].split("-")).map(Integer::valueOf).toArray(Integer[]::new);
            Integer[] second = Stream.of(assignmentRanges[1].split("-")).map(Integer::valueOf).toArray(Integer[]::new);

            if (isOverlapped(first, second) || isOverlapped(second, first)) {
                pairs++;
            }
        }
        return pairs;
    }


    private static Boolean isContained(Integer[] outer, Integer[] inner) {
        if (outer[0] <= inner[0] && outer[1] >= inner[1]) {
            return true;
        }
        return false;
    }

    private static Boolean isOverlapped(Integer[] first, Integer[] second) {
        if (first[0] >= second[0] && first[0] <= second[1]) {
            return true;
        }
        return false;
    }
}