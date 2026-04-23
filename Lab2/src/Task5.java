import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter cyan (C) value [0–100]: ");
        double c = scanner.nextDouble();
        System.out.print("Enter magenta (M) value [0–100]: ");
        double m = scanner.nextDouble();
        System.out.print("Enter yellow (Y) value [0–100]: ");
        double y = scanner.nextDouble();
        System.out.print("Enter black (K) value [0–100]: ");
        double k = scanner.nextDouble();

        int red = (int)Math.round(255 * (1 - c / 100) * (1 - k / 100));
        int green = (int)Math.round(255 * (1 - m / 100) * (1 - k / 100));
        int blue = (int)Math.round(255 * (1 - y / 100) * (1 - k / 100));

        System.out.println("Red: " + red);
        System.out.println("Green: " + green);
        System.out.println("Blue: " + blue);

        scanner.close();
    }
}
