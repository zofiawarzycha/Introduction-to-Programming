public class Circle {
    // Access modifier: PRIVATE
    // Why: Encapsulation principle. We hide the internal state (radius) to prevent
    // invalid modifications (e.g., setting a negative radius directly) from outside the class.
    private double radius;

    // Access modifier: PUBLIC
    // Why: This is part of the public API. External classes need to create instances of Circle.
    public Circle(double radius) {
        setRadius(radius);
    }

    // Access modifier: PUBLIC
    // Why: We provide controlled read access to the private field.
    public double getRadius() {
        return radius;
    }

    // Access modifier: PUBLIC
    // Why: We provide controlled write access. This method includes validation logic
    // to ensure the object remains in a valid state.
    public void setRadius(double radius) {
        if (radius < 0) {
            System.out.println("Radius cannot be negative. Setting to 0.");
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }
}