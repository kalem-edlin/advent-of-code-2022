import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class Solution {
    public static void main(String args []) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
            String topCrates = findTopCratesMulti(sc);
            System.out.println(topCrates);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found with exception " + e);
        }
    }

    private static String findTopCrates(Scanner sc) {
        String answer = "";
        Map<Integer, Stack<Character>> board = setUpBoardDataStructure(sc);
        System.out.println(board.keySet());
        while (sc.hasNextLine()) {
            String[] steps = sc.nextLine().split(" ");
            int amount = Integer.valueOf(steps[1]);
            int subjectNum = Integer.valueOf(steps[3]);
            int destinationNum = Integer.valueOf(steps[5]);
            Stack<Character> destination = board.get(destinationNum);
            Stack<Character> subject = board.get(subjectNum);
            for (int i = 0; i < amount; i++){
                destination.push(subject.pop());
            }
            board.put(destinationNum, destination);
            board.put(subjectNum, subject);
        }

        for(Stack<Character> stack: board.values()) {
            answer += stack.peek();
        }
        return answer;
    }

    private static String findTopCratesMulti(Scanner sc) {
        String answer = "";
        Map<Integer, Stack<Character>> board = setUpBoardDataStructure(sc);
        System.out.println(board.keySet());
        while (sc.hasNextLine()) {
            String[] steps = sc.nextLine().split(" ");
            int amount = Integer.valueOf(steps[1]);
            int subjectNum = Integer.valueOf(steps[3]);
            int destinationNum = Integer.valueOf(steps[5]);
            Stack<Character> destination = board.get(destinationNum);
            Stack<Character> subject = board.get(subjectNum);

            Character[] load = new Character[amount];
            for (int i = amount - 1; i >= 0; i--){
                load[i] = subject.pop();
            }

            for (int i = 0; i < amount; i++) {
                destination.push(load[i]);
            }
            
            board.put(destinationNum, destination);
            board.put(subjectNum, subject);
        }

        for(Stack<Character> stack: board.values()) {
            answer += stack.peek();
        }
        return answer;
    }

    private static Map<Integer, Stack<Character>> setUpBoardDataStructure(Scanner sc) {
        Map<Integer, Stack<Character>> board = new HashMap<Integer, Stack<Character>>();
        
        String next = sc.nextLine();
        while (next != "") {
            for (int k = 0; k < next.length(); k++) {
                char toAdd = next.charAt(k);
                if (!Character.isLetter(toAdd)) {
                    continue;
                }
                int pos = Math.toIntExact(((k - 1) / 4) + 1);
                Stack<Character> existingStack = board.get(pos);
                if (existingStack == null) {
                    existingStack = new Stack<Character>();
                }
                existingStack.add(0, toAdd);
                board.put(pos, existingStack);
            }
            next = sc.nextLine();
        }
        return board;
    }
}