public class Task2 {
    public static void main(String[] args) {
        int n = 5;
        printCollatzConjecture(n);
    }

    public static void printCollatzConjecture(int n) {
        System.out.println("Collatz conjecture (3n+1 problem) for n = " + n + ":");

        while (n != 1) {
            System.out.println(n);
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }

        System.out.println(1);
    }
}
