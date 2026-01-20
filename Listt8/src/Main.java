public class Main {
    public static void main(String[] args) {
        // 1. Circle
        double circleRadius = 5.0;
        Circle circle = new Circle(circleRadius);
        System.out.println("Circle Radius: " + circle.getRadius());
        System.out.printf("Area: %.2f%n", circle.calculateArea());
        System.out.printf("Circumference: %.2f%n", circle.calculateCircumference());

        // 2. Sphere
        double sphereRadius = 3.0;
        Sphere sphere = new Sphere(sphereRadius);
        System.out.println("\nSphere Radius: " + sphere.getRadius());
        System.out.printf("Surface Area: %.2f%n", sphere.calculateSurfaceArea());
        System.out.printf("Volume: %.2f%n", sphere.calculateVolume());

        // 3. Matrix
        int rows = 3;
        int cols = 3;

        System.out.println("\nMatrix A:");
        Matrix matrixA = new Matrix(rows, cols);
        matrixA.print();

        System.out.println("Transpose of A:");
        Matrix transposedA = matrixA.transpose();
        transposedA.print();

        // Spiral print has its own prefix in the method
        matrixA.printSpiral();

        System.out.println("Matrix B:");
        Matrix matrixB = new Matrix(rows, cols);
        matrixB.print();

        System.out.println("Multiplication (A * B):");
        Matrix product = matrixA.multiply(matrixB);
        if (product != null) {
            product.print();
        } else {
            System.out.println("Error: Dimension mismatch.");
        }
    }
}