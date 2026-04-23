import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double mass = scanner.nextDouble();
        double height = scanner.nextDouble();

        if (mass <= 0 || height <= 0) {
            System.out.println("Mass and height must be positive numbers.");
        } else {
            double bmi = mass / (height * height);
            System.out.println("BMI: " + bmi);

            if (bmi < 18.5) {
                System.out.println("Underweight");
            } else if (bmi < 25) {
                System.out.println("Normal");
            } else if (bmi < 30) {
                System.out.println("Overweight");
            } else {
                System.out.println("Obese");
            }
        }
    }
}
