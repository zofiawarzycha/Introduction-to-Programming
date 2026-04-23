import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter x: ");
        int x = scanner.nextInt();

        System.out.print("Enter y: ");
        int y = scanner.nextInt();

        System.out.print("Enter x0: ");
        int x0 = scanner.nextInt();

        System.out.print("Enter y0: ");
        int y0 = scanner.nextInt();

        double distance = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));
        System.out.println("Euclidean distance: " + String.format("%.2f", distance));

        scanner.close();
    }
}
