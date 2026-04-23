import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("Enter rows and columns:");
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] table = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                table[i][j] = r.nextInt(100) + 1;
            }
        }
        System.out.println("Original table:");
        printTable(table);

        int[][] trans = new int[n][m];
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                trans[j][i] = table[i][j];
        System.out.println("Transposed table:");
        printTable(trans);

        if(m == n) {
            int[][] table2 = new int[n][m];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    table2[i][j] = r.nextInt(100) + 1;
            int[][] multiplied = new int[m][m];
            for(int i = 0; i < m; i++)
                for(int j = 0; j < m; j++)
                    for(int k = 0; k < n; k++)
                        multiplied[i][j] += table[i][k] * table2[k][j];
            System.out.println("Multiplied table:");
            printTable(multiplied);
        } else {
            System.out.println("Multiplication skipped (dimensions mismatch).");
        }

        if(m == n) {
            int[][] rotated = new int[n][n];
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    rotated[j][n - 1 - i] = table[i][j];
            System.out.println("Rotated 90° table:");
            printTable(rotated);
        } else {
            System.out.println("Rotation skipped (not square).");
        }

        System.out.println("Spiral order:");
        printSpiral(table);

        // Task 6
        if(m == n) {
            boolean magic = isMagicSquare(table);
            System.out.println("Is magic square? " + magic);
        } else {
            System.out.println("Magic square check skipped (not square).");
        }

        System.out.println("Local maxima:");
        localMaxima(table);

        System.out.println("Jagged array creation:");
        System.out.println("Enter number of jagged rows:");
        int rows = sc.nextInt();
        int[][] jagged = new int[rows][];
        for(int i = 0; i < rows; i++) {
            System.out.println("Columns for row " + (i + 1) + ":");
            int cols = sc.nextInt();
            jagged[i] = new int[cols];
            for(int j = 0; j < cols; j++)
                jagged[i][j] = r.nextInt(100) + 1;
        }
        printJagged(jagged);

        // Task 9
        System.out.println("Common elements in a new table:");
        int[][] arr = new int[m][n];
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                arr[i][j] = r.nextInt(6) + 1;
        printTable(arr);
        ArrayList<Integer> common = commonElements(arr);
        if (common.size() == 0) System.out.println("No common elements.");
        else System.out.println(common);

        sc.close();
    }

    public static void printTable(int[][] t) {
        for(int i = 0; i < t.length; i++) {
            for(int j = 0; j < t[i].length; j++)
                System.out.print(t[i][j] + "\t");
            System.out.println();
        }
    }

    public static void printJagged(int[][] t) {
        for(int i = 0; i < t.length; i++) {
            for(int j = 0; j < t[i].length; j++)
                System.out.print(t[i][j] + "\t");
            System.out.println();
        }
    }

    public static void printSpiral(int[][] t) {
        int m = t.length, n = t[0].length;
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        while(top <= bottom && left <= right) {
            for(int i = left; i <= right; i++) System.out.print(t[top][i] + " ");
            top++;
            for(int i = top; i <= bottom; i++) System.out.print(t[i][right] + " ");
            right--;
            if(top <= bottom)
                for(int i = right; i >= left; i--) System.out.print(t[bottom][i] + " ");
            bottom--;
            if(left <= right)
                for(int i = bottom; i >= top; i--) System.out.print(t[i][left] + " ");
            left++;
        }
        System.out.println();
    }

    public static boolean isMagicSquare(int[][] t) {
        int n = t.length, target = 0;
        for(int i = 0; i < n; i++) target += t[0][i];
        for(int i = 1; i < n; i++) {
            int rowSum = 0;
            for(int j = 0; j < n; j++) rowSum += t[i][j];
            if(rowSum != target) return false;
        }
        for(int i = 0; i < n; i++) {
            int colSum = 0;
            for(int j = 0; j < n; j++) colSum += t[j][i];
            if(colSum != target) return false;
        }
        int d1 = 0, d2 = 0;
        for(int i = 0; i < n; i++) {
            d1 += t[i][i];
            d2 += t[i][n - 1 - i];
        }
        return (d1 == target && d2 == target);
    }

    public static void localMaxima(int[][] t) {
        int m = t.length, n = t[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                boolean localmax = true;
                int val = t[i][j];
                if(i > 0 && t[i - 1][j] >= val) localmax = false;
                if(i < m - 1 && t[i + 1][j] >= val) localmax = false;
                if(j > 0 && t[i][j - 1] >= val) localmax = false;
                if(j < n - 1 && t[i][j + 1] >= val) localmax = false;
                if(localmax) System.out.println("Row:" + i + " Col:" + j + " Val:" + val);
            }
        }
    }

    public static ArrayList<Integer> commonElements(int[][] t) {
        int m = t.length, n = t[0].length;
        HashSet<Integer> set = new HashSet<>();
        for(int j = 0; j < n; j++) set.add(t[0][j]);
        for(int i = 1; i < m; i++) {
            HashSet<Integer> rowSet = new HashSet<>();
            for(int j = 0; j < n; j++) rowSet.add(t[i][j]);
            set.retainAll(rowSet);
        }
        return new ArrayList<>(set);
    }
}
