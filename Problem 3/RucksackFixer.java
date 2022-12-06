import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

class RucksackFixer {
    public static void main(String args []) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            int totalPriorities = countPriorities(sc);
            System.out.println(totalPriorities);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found with exception " + e);
        }
    }

    // had double for loops initially but wanted to try out a mpa solution
    private static int countPriorities(Scanner sc) {
        int totalPriorities = 0;
        int baseCode = Character.hashCode('A');

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Map<Character, Boolean> map = new HashMap<Character, Boolean>();
            for (int i = 0; i < line.length(); i++) {
                Character item = line.charAt(i);
                Boolean exists = map.get(item);
                if (i >= line.length() / 2 && !Objects.isNull(exists)) {
                    int upperCaseCode = Character.toUpperCase(item);
                    int pos = upperCaseCode - baseCode;
                    totalPriorities += pos + (item.hashCode() == upperCaseCode ? 27 : 1);
                    break;
                } else {
                    if (i < line.length() / 2) {
                        map.put(item, true);
                    }
                }
            } 
        }
        return totalPriorities;
    }

    private static int countBadgePriorities(Scanner sc) {
        int totalPriorities = 0;
        int baseCode = Character.hashCode('A');

        while (sc.hasNextLine()) {
            String group1 = sc.nextLine();
            String group2 = sc.nextLine();
            String group3 = sc.nextLine();
            outer: 
            for (int i = 0; i < group1.length(); i++) {
                Character item = group1.charAt(i);
                for (int k = 0; k < group2.length(); k++) {
                    for (int j = 0; j < group3.length(); j++) {
                        if (item.hashCode() == group2.codePointAt(k) && item.hashCode() == group3.codePointAt(j)) {
                            int upperCaseCode = Character.toUpperCase(item);
                            int pos = upperCaseCode - baseCode;
                            totalPriorities += pos + (item.hashCode() == upperCaseCode ? 27 : 1);
                            break outer;
                        }
                    }
                }
            } 
        }
        return totalPriorities;
    }

    // private static int countBadgePriorities(Scanner sc) {
    //     int totalPriorities = 0;
    //     int baseCode = Character.hashCode('A');

    //     while (sc.hasNextLine()) {
    //         String group1 = sc.nextLine();
    //         String group2 = sc.nextLine();
    //         String group3 = sc.nextLine();
    //         outer: 
    //         for (int i = 0; i < group1.length(); i++) {
    //             Character item = group1.charAt(i);
    //             for (int k = 0; k < group2.length(); k++) {
    //                 for (int j = 0; j < group3.length(); j++) {
    //                     if (item.hashCode() == group2.codePointAt(k) && item.hashCode() == group3.codePointAt(j)) {
    //                         int upperCaseCode = Character.toUpperCase(item);
    //                         int pos = upperCaseCode - baseCode;
    //                         totalPriorities += pos + (item.hashCode() == upperCaseCode ? 27 : 1);
    //                         break outer;
    //                     }
    //                 }
    //             }
    //         } 
    //     }
    //     return totalPriorities;
    // }
}