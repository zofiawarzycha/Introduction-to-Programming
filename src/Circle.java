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

    /**
     * Retrieves the current radius of the circle.
     * Access modifier: PUBLIC
     * Why: We provide controlled read access to the private field.
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius with validation.
     * Access modifier: PUBLIC
     * Why: We provide controlled write access to ensure the object remains in a valid state.
     * @param radius the new radius to set
     */
    public void setRadius(double radius) {
        if (radius < 0) {
            System.out.println("Error: Radius cannot be negative. Setting to 0.");
            this.radius = 0;
        } else {
            this.radius = radius;
        }
    }
}