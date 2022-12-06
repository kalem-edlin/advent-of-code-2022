import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class StrategyGuideInterpreter {
    public static void main(String args []) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            int totalScore = findProperStrategyScore(sc);
            System.out.println(totalScore);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found with exception " + e);
        }
    }
    
    static private int findStrategyScore(Scanner sc) {
        int totalScore = 0;
        while (sc.hasNextLine()) {
            String[] moves = sc.nextLine().split(" ");
            int you = moves[0].codePointAt(0) - "A".codePointAt(0);
            int me = moves[1].codePointAt(0) - "X".codePointAt(0);

            if (you == me) { totalScore += 3; }
            else if ((you + 1) % 3 == me) { totalScore += 6; }

            totalScore += me + 1;
        }
        return totalScore;
    }

    static private int findProperStrategyScore(Scanner sc) {
        int totalScore = 0;
        while (sc.hasNextLine()) {
            String[] lineArr = sc.nextLine().split(" ");
            int you = lineArr[0].codePointAt(0) - "A".codePointAt(0);
            int outcome = lineArr[1].codePointAt(0) - "X".codePointAt(0);

            int roundPoints = outcome * 3;
            int offset = (outcome + 2) % 3;
            int me = (you + offset) % 3;

            totalScore += 1 + me + roundPoints;
        }
        return totalScore;
    }
}

