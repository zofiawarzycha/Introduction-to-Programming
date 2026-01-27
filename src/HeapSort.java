/**
 * Implements the Heap Sort algorithm.
 * This class uses the MaxHeap logic to sort an array of integers.
 */
public class HeapSort {

    /**
     * Sorts an array of integers using the Heap Sort algorithm.
     * * Algorithm Steps:
     * 1. Build a Max Heap from the input data.
     * 2. At this point, the largest item is stored at the root of the heap. 
     * Replace it with the last item of the heap followed by reducing the size of heap by 1. 
     * Finally, heapify the root of the tree.
     * 3. Repeat step 2 while the size of the heap is greater than 1.
     *
     * @param arr The array to be sorted.
     */
    public void sort(int[] arr) {
        int n = arr.length;

        // Step 1: Build heap (rearrange array)
        // Start from the last non-leaf node and move up to the root
        for (int i = n / 2 - 1; i >= 0; i--) {
            MaxHeap.maxHeapify(arr, n, i);
        }

        // Step 2: Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root (maximum value) to the end (sorted region)
            MaxHeap.swap(arr, 0, i);

            // Call maxHeapify on the reduced heap
            MaxHeap.maxHeapify(arr, i, 0);
        }
    }
}