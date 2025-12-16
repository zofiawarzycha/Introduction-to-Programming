public class Sphere {
    // Access modifier: PRIVATE
    // Why: Encapsulation. Restricts direct access to data, protecting it from inconsistent states.
    private double radius;

    // Access modifier: PUBLIC
    // Why: Allows other parts of the program to create Sphere objects.
    public Sphere(double radius) {
        setRadius(radius);
    }

    // Access modifier: PUBLIC
    // Why: Exposes the value of the private field safely.
    public double getRadius() {
        return radius;
    }

    // Access modifier: PUBLIC
    // Why: Allows modifying the radius while enforcing validation rules (non-negative).
    public void setRadius(double radius) {
        if (radius < 0) {
            System.out.println("Radius cannot be negative. Setting to 0.");
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }
}