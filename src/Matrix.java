public class Matrix {
    // Access modifier: PRIVATE
    // Why: The array and dimensions are tightly coupled. Modifying 'rows' without resizing
    // 'data' would crash the program. We keep them private to ensure consistency.
    private int[][] data;
    private int rows;
    private int cols;

    // Access modifier: PUBLIC
    // Why: Read-only access to dimensions is safe and necessary for external calculations.
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}