import java.util.Random;

public class Matrix {
    // Access modifier: PRIVATE
    // Why: The array and dimensions are tightly coupled. Modifying 'rows' without resizing
    // 'data' would crash the program. We keep them private to ensure consistency.
    private int[][] data;
    private int rows;
    private int cols;

    /**
     * Constructor that initializes the matrix with random values.
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
        // Task 9: Logic broken into a meaningful helper method
        populateWithRandomValues();
    }

    /**
     * Private constructor for internal use (creating empty result matrices).
     */
    private Matrix(int rows, int cols, boolean empty) {
        this.rows = rows;
        this.cols = cols;
        this.data = new int[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    /**
     * Helper method to populate the matrix with random numbers.
     * Task 13: Computational Complexity: O(rows * cols) - we iterate through every cell once.
     */
    private void populateWithRandomValues() {
        Random random = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                data[row][col] = random.nextInt(100) + 1;
            }
        }
    }

    /**
     * Prints the matrix to the console.
     * Task 13: Computational Complexity: O(rows * cols) - linear relative to number of elements.
     */
    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(data[row][col] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Transposes the matrix.
     * Task 13: Computational Complexity: O(rows * cols) - we visit every element once to move it.
     * @return a new Matrix object representing the transpose
     */
    public Matrix transpose() {
        Matrix transposed = new Matrix(cols, rows, true);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                transposed.data[col][row] = this.data[row][col];
            }
        }
        return transposed;
    }

    /**
     * Multiplies this matrix by another matrix.
     * Task 13: Computational Complexity: O(rows * cols * other.cols) - Standard cubic complexity for matrix multiplication.
     * @param other the matrix to multiply by
     * @return result Matrix or null if dimensions mismatch
     */
    public Matrix multiply(Matrix other) {
        if (this.cols != other.rows) {
            System.out.println("Multiplication impossible: Dimensions mismatch.");
            return null;
        }
        Matrix result = new Matrix(this.rows, other.cols, true);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < this.cols; k++) {
                    result.data[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Prints the matrix elements in spiral order.
     * Task 13: Computational Complexity: O(rows * cols) - each element is visited and printed exactly once.
     */
    public void printSpiral() {
        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;

        System.out.print("Spiral: ");
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) System.out.print(data[top][i] + " ");
            top++;
            for (int i = top; i <= bottom; i++) System.out.print(data[i][right] + " ");
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) System.out.print(data[bottom][i] + " ");
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) System.out.print(data[i][left] + " ");
                left++;
            }
        }
        System.out.println();
    }
}