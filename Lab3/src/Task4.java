import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double income = scanner.nextDouble();
        double tax;

        if (income <= 25000) {
            tax = income * 0.15;
        } else if (income <= 50000) {
            tax = 3750 + 0.28 * (income - 25000);
        } else if (income <= 75000) {
            tax = 10750 + 0.31 * (income - 50000);
        } else {
            tax = 18500 + 0.40 * (income - 75000);
        }

        System.out.println("Income tax: $" + tax);
    }
}
