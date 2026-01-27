/**
 * A utility class representing the logic for a Max Heap data structure.
 * This class provides static methods to manipulate arrays into a Max Heap structure.
 */
public class MaxHeap {

    /**
     * Maintains the Max Heap property for a subtree rooted at index 'i'.
     * This assumes that the subtrees rooted at left and right children are already heaps.
     *
     * @param arr The array representing the heap.
     * @param n   The size of the heap (number of elements to consider).
     * @param i   The index of the root of the subtree to heapify.
     */
    public static void maxHeapify(int[] arr, int n, int i) {
        int largest = i;          // Initialize largest as root
        int left = 2 * i + 1;     // Left child index
        int right = 2 * i + 2;    // Right child index

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);

            // Recursively heapify the affected sub-tree
            maxHeapify(arr, n, largest);
        }
    }

    /**
     * Swaps two elements in an integer array.
     *
     * @param arr The array containing the elements.
     * @param i   The index of the first element.
     * @param j   The index of the second element.
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}