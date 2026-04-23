import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the radius (R): ");
        double R = scanner.nextDouble();

        double circlePerimeter = 2 * Math.PI * R;
        double circleArea = Math.PI * R * R;
        double sphereSurfaceArea = 4 * Math.PI * R * R;
        double sphereVolume = (4.0 / 3.0) * Math.PI * R * R * R;

        System.out.println("Perimeter of the circle: " + String.format("%.2f", circlePerimeter));
        System.out.println("Area of the circle: " + String.format("%.2f", circleArea));
        System.out.println("Surface area of the sphere: " + String.format("%.2f", sphereSurfaceArea));
        System.out.println("Volume of the sphere: " + String.format("%.2f", sphereVolume));

        scanner.close();
    }
}
