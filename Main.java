import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    // https://www.codewars.com/kata/549ee8b47111a81214000941/train/java
    public static int knight(String start, String  finish) { // monte-carlo method with strings
        List<String> list;
        String currentStart;
        int steps;
        int minSteps = Integer.MAX_VALUE;
        for (int probe = 0; probe <= 500; probe++) {
            steps = 0;
            currentStart = start;
            while (!currentStart.equals(finish)) {
                list = legalMovesFrom(currentStart);
                currentStart = list.get((int) (Math.random() * list.size()));
                steps++;
            }
            if (steps < minSteps) minSteps = steps;

        }
        return minSteps;
    }

//    public static int knight(String start, String finish) { // with Manhattan distances
//        int steps = 999;
//        int[] startArray = new int[]{getFirstNumber(start.charAt(0)), Character.getNumericValue(start.charAt(1))};
//        int[] finishArray = new int[]{getFirstNumber(finish.charAt(0)), Character.getNumericValue(finish.charAt(1))};
//        int manhattanDistance = Math.abs(startArray[0] - finishArray[0]) + Math.abs(startArray[1] - finishArray[1]);
//        System.out.println(" manhattanDistance = " + manhattanDistance);
//        if (manhattanDistance == 1) steps = 3;
//        else if (manhattanDistance == 2) {
//            if (((startArray[0] == 1 && startArray[1] == 1) || (startArray[0] == 1 && startArray[1] == 8) ||
//                    (startArray[0] == 8 && startArray[1] == 1) || (startArray[0] == 8 && startArray[1] == 8) ||
//                    (finishArray[0] == 1 && finishArray[1] == 1) || (finishArray[0] == 1 && finishArray[1] == 8) ||
//                    (finishArray[0] == 8 && finishArray[1] == 1) || (finishArray[0] == 8 && finishArray[1] == 8)) &&
//                    (startArray[0] != finishArray[0] && startArray[1] != finishArray[1])) steps = 4;
//            else steps = 2;
//        }
//        else if (manhattanDistance == 3) {
//            if (startArray[0] == finishArray[0] || startArray[1] == finishArray[1]) steps = 3;
//            else steps = 1;
//        }
//        else if (manhattanDistance == 4) {
//            if (Math.abs(startArray[0] - finishArray[0]) == 2) steps = 4;
//            else steps = 2;
//        }
//        else if (manhattanDistance == 5) steps = 3;
//        else if (manhattanDistance == 6) {
//            if (Math.abs(startArray[0] - finishArray[0]) == 2 || Math.abs(startArray[1] - finishArray[1]) == 2 ||
//                Math.abs(startArray[0] - finishArray[0]) == 3 || Math.abs(startArray[1] - finishArray[1]) == 3 ||
//                Math.abs(startArray[0] - finishArray[0]) == 4 || Math.abs(startArray[1] - finishArray[1]) == 4) steps = 2;
//            else steps = 4;
//        }
//        else if (manhattanDistance == 7) {
//            if (Math.abs(startArray[0] - finishArray[0]) == 1 || Math.abs(startArray[1] - finishArray[1]) == 1 ||
//                Math.abs(startArray[0] - finishArray[0]) == 2 || Math.abs(startArray[1] - finishArray[1]) == 2 ||
//                Math.abs(startArray[0] - finishArray[0]) == 3 || Math.abs(startArray[1] - finishArray[1]) == 3 ||
//                    Math.abs(startArray[0] - finishArray[0]) == 4 || Math.abs(startArray[1] - finishArray[1]) == 4 ||
//                    Math.abs(startArray[0] - finishArray[0]) == 5 || Math.abs(startArray[1] - finishArray[1]) == 5) steps = 3;
//            else steps = 5;
//        }
//        else if (manhattanDistance == 8) steps = 4;
//        else if (manhattanDistance == 9) {
//            if (Math.abs(startArray[0] - finishArray[0]) == 3 || Math.abs(startArray[1] - finishArray[1]) == 3 ||
//                    Math.abs(startArray[0] - finishArray[0]) == 4 || Math.abs(startArray[1] - finishArray[1]) == 4 ||
//                    Math.abs(startArray[0] - finishArray[0]) == 5 || Math.abs(startArray[1] - finishArray[1]) == 5 ||
//                    Math.abs(startArray[0] - finishArray[0]) == 6 || Math.abs(startArray[1] - finishArray[1]) == 6) steps = 3;
//            else steps = 5;
//        }
//        else if (manhattanDistance == 10) steps = 4;
//        else if (manhattanDistance == 11) steps = 5;
//        else if (manhattanDistance == 12) steps = 4;
//        else if (manhattanDistance == 13) steps = 5;
//        else if (manhattanDistance == 14) steps = 6;
//        return steps;
//    }

    private static int[] moveWithMinManhattanDistance(List<int[]> list, int[] finishArray) {
        int[] distances = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            distances[i] = Math.abs(list.get(i)[0] - finishArray[0]) + Math.abs(list.get(i)[1] - finishArray[1]);
            if (distances[i] == 1) distances[i] = 999;
        }
        Arrays.sort(distances);
        int minDistance = distances[0];
        for (int[] i : list)
            if (Math.abs(i[0] - finishArray[0]) + Math.abs(i[1] - finishArray[1]) == minDistance) return i;
        return new int[]{999, 999}; //magic number
    }

    private static int getFirstNumber(char c) {
        if (c == 'a') return 1;
        else if (c == 'b') return 2;
        else if (c == 'c') return 3;
        else if (c == 'd') return 4;
        else if (c == 'e') return 5;
        else if (c == 'f') return 6;
        else if (c == 'g') return 7;
        else if (c == 'h') return 8;
        else {
            System.out.println("Error: not correct first letter");
            return 999; //magic number
        }
    }

        private static List<String> legalMovesFrom(String str) { // monte-carlo method with strings
        List<String> list = new ArrayList<>();
        String nextMove = "";
        // 2-8 variants
        char c1 = str.charAt(0);
        char c2 = str.charAt(1);
        //1
        if (c1 != 'h' && c2 != '7' && c2 != '8') {
            // create first letter
            if (c1 == 'a') nextMove = "b";
            else if (c1 == 'b') nextMove = "c";
            else if (c1 == 'c') nextMove = "d";
            else if (c1 == 'd') nextMove = "e";
            else if (c1 == 'e') nextMove = "f";
            else if (c1 == 'f') nextMove = "g";
            else if (c1 == 'g') nextMove = "h";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '1') nextMove += "3";
            else if (c2 == '2') nextMove += "4";
            else if (c2 == '3') nextMove += "5";
            else if (c2 == '4') nextMove += "6";
            else if (c2 == '5') nextMove += "7";
            else if (c2 == '6') nextMove += "8";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //2
        if (c1 != 'g' && c1 != 'h' && c2!= '8') {
            // create first letter
            if (c1 == 'a') nextMove = "c";
            else if (c1 == 'b') nextMove = "d";
            else if (c1 == 'c') nextMove = "e";
            else if (c1 == 'd') nextMove = "f";
            else if (c1 == 'e') nextMove = "g";
            else if (c1 == 'f') nextMove = "h";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '1') nextMove += "2";
            else if (c2 == '2') nextMove += "3";
            else if (c2 == '3') nextMove += "4";
            else if (c2 == '4') nextMove += "5";
            else if (c2 == '5') nextMove += "6";
            else if (c2 == '6') nextMove += "7";
            else if (c2 == '7') nextMove += "8";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //3
        if (c1 != 'g' && c1 != 'h' && c2!= '1') {
            // create first letter
            if (c1 == 'a') nextMove = "c";
            else if (c1 == 'b') nextMove = "d";
            else if (c1 == 'c') nextMove = "e";
            else if (c1 == 'd') nextMove = "f";
            else if (c1 == 'e') nextMove = "g";
            else if (c1 == 'f') nextMove = "h";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '2') nextMove += "1";
            else if (c2 == '3') nextMove += "2";
            else if (c2 == '4') nextMove += "3";
            else if (c2 == '5') nextMove += "4";
            else if (c2 == '6') nextMove += "5";
            else if (c2 == '7') nextMove += "6";
            else if (c2 == '8') nextMove += "7";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //4
        if (c1 != 'h' && c2 != '1' && c2 != '2') {
            // create first letter
            if (c1 == 'a') nextMove = "b";
            else if (c1 == 'b') nextMove = "c";
            else if (c1 == 'c') nextMove = "d";
            else if (c1 == 'd') nextMove = "e";
            else if (c1 == 'e') nextMove = "f";
            else if (c1 == 'f') nextMove = "g";
            else if (c1 == 'g') nextMove = "h";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '3') nextMove += "1";
            else if (c2 == '4') nextMove += "2";
            else if (c2 == '5') nextMove += "3";
            else if (c2 == '6') nextMove += "4";
            else if (c2 == '7') nextMove += "5";
            else if (c2 == '8') nextMove += "6";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //5
        if (c1 != 'a' && c2 != '1' && c2 != '2') {
            // create first letter
            if (c1 == 'b') nextMove = "a";
            else if (c1 == 'c') nextMove = "b";
            else if (c1 == 'd') nextMove = "c";
            else if (c1 == 'e') nextMove = "d";
            else if (c1 == 'f') nextMove = "e";
            else if (c1 == 'g') nextMove = "f";
            else if (c1 == 'h') nextMove = "g";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '3') nextMove += "1";
            else if (c2 == '4') nextMove += "2";
            else if (c2 == '5') nextMove += "3";
            else if (c2 == '6') nextMove += "4";
            else if (c2 == '7') nextMove += "5";
            else if (c2 == '8') nextMove += "6";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //6
        if (c1 != 'a' && c1 != 'b' && c2!= '1') {
            // create first letter
            if (c1 == 'c') nextMove = "a";
            else if (c1 == 'd') nextMove = "b";
            else if (c1 == 'e') nextMove = "c";
            else if (c1 == 'f') nextMove = "d";
            else if (c1 == 'g') nextMove = "e";
            else if (c1 == 'h') nextMove = "f";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '2') nextMove += "1";
            else if (c2 == '3') nextMove += "2";
            else if (c2 == '4') nextMove += "3";
            else if (c2 == '5') nextMove += "4";
            else if (c2 == '6') nextMove += "5";
            else if (c2 == '7') nextMove += "6";
            else if (c2 == '8') nextMove += "7";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //7
        if (c1 != 'a' && c1 != 'b' && c2!= '8') {
            // create first letter
            if (c1 == 'c') nextMove = "a";
            else if (c1 == 'd') nextMove = "b";
            else if (c1 == 'e') nextMove = "c";
            else if (c1 == 'f') nextMove = "d";
            else if (c1 == 'g') nextMove = "e";
            else if (c1 == 'h') nextMove = "f";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '1') nextMove += "2";
            else if (c2 == '2') nextMove += "3";
            else if (c2 == '3') nextMove += "4";
            else if (c2 == '4') nextMove += "5";
            else if (c2 == '5') nextMove += "6";
            else if (c2 == '6') nextMove += "7";
            else if (c2 == '7') nextMove += "8";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }
        //8
        if (c1 != 'a' && c2 != '7' && c2 != '8') {
            // create first letter
            if (c1 == 'b') nextMove = "a";
            else if (c1 == 'c') nextMove = "b";
            else if (c1 == 'd') nextMove = "c";
            else if (c1 == 'e') nextMove = "d";
            else if (c1 == 'f') nextMove = "e";
            else if (c1 == 'g') nextMove = "f";
            else if (c1 == 'h') nextMove = "g";
            else System.out.println("Error: not correct first letter");
            // create second letter
            if (c2 == '1') nextMove += "3";
            else if (c2 == '2') nextMove += "4";
            else if (c2 == '3') nextMove += "5";
            else if (c2 == '4') nextMove += "6";
            else if (c2 == '5') nextMove += "7";
            else if (c2 == '6') nextMove += "8";
            else System.out.println("Error: not correct second letter");
            list.add(nextMove);
        }

        return list;
    }
    private static List<int[]> legalMovesFrom(int f, int s) { // Monte-Carlo method with numbers
        List<int[]> list = new ArrayList<>();
        int[] arr;
        //about f parameter: a-1, b-2, c-3, d-4, e-5, f-6, g-7, h-8
        //1 (2-8 variants)
        if (f != 8 && s != 7 && s != 8) {
            arr = new int[2];
            arr[0] = f + 1;
            arr[1] = s + 2;
            list.add(arr);
        }
        //2
        if (f != 7 && f != 8 && s != 8) {
            arr = new int[2];
            arr[0] = f + 2;
            arr[1] = s + 1;
            list.add(arr);
        }
        //3
        if (f != 7 && f != 8 && s != 1) {
            arr = new int[2];
            arr[0] = f + 2;
            arr[1] = s - 1;
            list.add(arr);
        }
        //4
        if (f != 8 && s != 1 && s != 2) {
            arr = new int[2];
            arr[0] = f + 1;
            arr[1] = s - 2;
            list.add(arr);
        }
        //5
        if (f != 1 && s != 1 && s != 2) {
            arr = new int[2];
            arr[0] = f - 1;
            arr[1] = s - 2;
            list.add(arr);
        }
        //6
        if (f != 1 && f != 2 && s != 1) {
            arr = new int[2];
            arr[0] = f - 2;
            arr[1] = s - 1;
            list.add(arr);
        }
        //7
        if (f != 1 && f != 2 && s != 8) {
            arr = new int[2];
            arr[0] = f - 2;
            arr[1] = s + 1;
            list.add(arr);
        }
        //8
        if (f != 1 && s != 7 && s != 8) {
            arr = new int[2];
            arr[0] = f - 1;
            arr[1] = s + 2;
            list.add(arr);
        }

        return list;
    }

    public static void main(String[] args) {
//        assertEquals("Test for (a1, c1)", 2, Chess.knight("a1", "c1"));
//        assertEquals("Test for (a1, f1)", 3, Chess.knight("a1", "f1"));
//        assertEquals("Test for (a1, f3)", 3, Chess.knight("a1", "f3"));
//        assertEquals("Test for (a1, f4)", 4, Chess.knight("a1", "f4"));
//        assertEquals("Test for (a1, f7)", 5, Chess.knight("a1", "f7"));
        // 4
        System.out.println(knight("a1", "c1"));
    }
}
