import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Driver class to demonstrate the functionality of the Heap Sort implementation.
 */
public class Main {

    /**
     * The main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Get user input for size
        System.out.print("Enter the number of random integers to generate: ");
        int size = 0;
        try {
            size = Integer.parseInt(scanner.nextLine());
            if (size <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid positive integer.");
            return;
        }

        // 2. Generate random integers
        int[] data = generateRandomArray(size);

        // Print a preview if size is small, otherwise just print stats
        System.out.println("\nGenerated " + size + " random integers.");
        if (size <= 20) {
            System.out.println("Original Array: " + Arrays.toString(data));
        }

        // 3. Perform Heap Sort
        HeapSort sorter = new HeapSort();
        long startTime = System.nanoTime();
        sorter.sort(data);
        long endTime = System.nanoTime();

        // 4. Output results
        if (size <= 20) {
            System.out.println("Sorted Array:   " + Arrays.toString(data));
        }
        System.out.printf("Sorting completed in %.3f ms.%n", (endTime - startTime) / 1e6);

        // 5. Verify correctness
        if (isSorted(data)) {
            System.out.println("VERIFICATION: The array is correctly sorted.");
        } else {
            System.err.println("VERIFICATION FAILED: The array is NOT sorted.");
        }

        // 6. Stability Analysis Output
        printStabilityAnalysis();

        scanner.close();
    }

    /**
     * Generates an array of random integers of a user-defined size.
     *
     * @param size The number of integers to generate.
     * @return An array containing random integers.
     */
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000); // Generates numbers between 0-999
        }
        return arr;
    }

    /**
     * verifies that the array is sorted in ascending order.
     *
     * @param arr The array to check.
     * @return true if sorted, false otherwise.
     */
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the investigation regarding the stability of the Heap Sort algorithm.
     */
    public static void printStabilityAnalysis() {
        System.out.println("\n--- Stability Analysis ---");
        System.out.println("Is Heap Sort Stable? NO.");
        System.out.println("Justification:");
        System.out.println("Heap Sort is unstable because operations on the heap can change the relative");
        System.out.println("order of equal elements. During the extraction phase, the root (maximum)");
        System.out.println("is swapped with the last element in the heap (a leaf). This long-distance");
        System.out.println("swap can move a leaf node past other nodes with the same value, disrupting");
        System.out.println("their original relative order.");
    }
}