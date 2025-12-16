public class Matrix {
    // Access modifier: PRIVATE
    // Why: The array and dimensions are tightly coupled. Modifying 'rows' without resizing
    // 'data' would crash the program. We keep them private to ensure consistency.
    private int[][] data;
    private int rows;
    private int cols;

    /**
     * Gets the number of rows.
     * Access modifier: PUBLIC
     * Why: Read-only access to dimensions is safe and necessary for external calculations.
     * @return row count
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of columns.
     * Access modifier: PUBLIC
     * Why: Read-only access to dimensions is safe.
     * @return column count
     */
    public int getCols() {
        return cols;
    }

    // Note: No setters for rows/cols/data to ensure immutability of dimensions
    // after the matrix is created.
}