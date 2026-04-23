import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("\nTask 1: First n Fibonacci numbers");
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        if (n > 0) {
            runFibonacci(n);
        } else {
            System.out.println("Given 'n' must be positive.");
        }

        System.out.println("\nTask 2: Find the following in the following ArrayList:");
        ArrayList<Integer> list = new ArrayList<>();
        int listSize = 10;
        for (int i = 0; i < listSize; i++) {
            list.add(random.nextInt(10) + 1);
        }
        System.out.println("Generated list: " + list);
        runArrayListStats(list);

        System.out.println("\nTask 3: Matrix operations");
        System.out.print("Enter matrix dimension n: ");
        int dim = scanner.nextInt();
        if (dim > 0) {
            runMatrixTask(dim, random);
        } else {
            System.out.println("Dimension must be positive!");
        }

        System.out.println("\nTask 4: Guessing Game");
        playGuessingGame(scanner, random);

        System.out.println("\nTask 5: Text Analysis");
        System.out.println("Enter text:");
        scanner.nextLine();
        String text = scanner.nextLine();
        runStringAnalysis(text);

        scanner.close();
    }

    public static void runFibonacci(int n) {
        long[] fib = new long[n];
        if (n >= 1) fib[0] = 0;
        if (n >= 2) fib[1] = 1;

        for (int i = 2; i < n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        long sum = 0;
        System.out.print("\nFibonacci sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print(fib[i] + " ");
            sum += fib[i];
        }
        System.out.println();
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + ((double) sum / n));
    }

    public static void runArrayListStats(ArrayList<Integer> list) {
        if (list.isEmpty()) return;

        int min = list.get(0);
        int max = list.get(0);
        double sum = 0;

        for (int val : list) {
            if (val < min) min = val;
            if (val > max) max = val;
            sum += val;
        }

        System.out.println("\nMin: " + min);
        System.out.println("Max: " + max);
        System.out.println("Average: " + (sum / list.size()));
        System.out.println("Occurences:");

        boolean[] visited = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            if (visited[i]) continue;
            int count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    visited[j] = true;
                    count++;
                }
            }
            System.out.println(list.get(i) + ": " + count);
        }
    }

    public static void runMatrixTask(int n, Random random) {
        int[][] m1 = new int[n][n];
        int[][] m2 = new int[n][n];

        System.out.println("\nMatrix 1:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m1[i][j] = random.nextInt(10);
                System.out.print(m1[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nMatrix 2:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m2[i][j] = random.nextInt(10);
                System.out.print(m2[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nSum of matrices:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((m1[i][j] + m2[i][j]) + "\t");
            }
            System.out.println();
        }

        System.out.println("\nElement-by-element multiplication of matrices:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((m1[i][j] * m2[i][j]) + "\t");
            }
            System.out.println();
        }
    }

    public static void playGuessingGame(Scanner scanner, Random random) {
        int target = random.nextInt(100) + 1;
        int guess = -1;

        while (guess != target) {
            System.out.print("\nGuess a number (1-100): ");
            guess = scanner.nextInt();

            if (guess < 1 || guess > 100) {
                System.out.println("Error: The number must be between 1 and 100.");
                continue;
            }

            if (guess > target) {
                System.out.println("Too high (guess smaller)");
            } else if (guess < target) {
                System.out.println("Too low (guess higher)");
            } else {
                System.out.println("Congratulations! You guessed right!");
            }
        }
    }

    public static void runStringAnalysis(String text) {
        System.out.println("Word count: " + countWords(text));

        int[] charCounts = countCharacters(text);
        System.out.println("Chars (with spaces): " + charCounts[0]);
        System.out.println("Chars (no spaces): " + charCounts[1]);

        int[] caseCounts = countCase(text);
        System.out.println("Uppercase: " + caseCounts[0]);
        System.out.println("Lowercase: " + caseCounts[1]);

        System.out.println("Digits: " + countDigits(text));
    }

    public static int countWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public static int[] countCharacters(String text) {
        int withSpaces = text.length();
        int withoutSpaces = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                withoutSpaces++;
            }
        }
        return new int[]{withSpaces, withoutSpaces};
    }

    public static int[] countCase(String text) {
        int upper = 0;
        int lower = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) upper++;
            else if (Character.isLowerCase(c)) lower++;
        }
        return new int[]{upper, lower};
    }

    public static int countDigits(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}