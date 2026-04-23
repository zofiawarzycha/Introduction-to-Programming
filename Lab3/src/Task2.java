import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2.0 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2.0 * a);
            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2.0 * a);
            System.out.println("Root: " + root);
        } else {
            double realPart = -b / (2.0 * a);
            double imagPart = Math.sqrt(-discriminant) / (2.0 * a);
            System.out.println("Root 1: " + realPart + " + " + imagPart + "i");
            System.out.println("Root 2: " + realPart + " - " + imagPart + "i");
        }
    }
}
