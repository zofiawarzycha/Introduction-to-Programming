public class Sphere {
    // Access modifier: PRIVATE
    // Why: Encapsulation. Restricts direct access to data, protecting it from inconsistent states.
    private double radius;

    // Access modifier: PUBLIC
    // Why: Allows other parts of the program to create Sphere objects.
    public Sphere(double radius) {
        setRadius(radius);
    }

    /**
     * Retrieves the radius of the sphere.
     * Access modifier: PUBLIC
     * Why: Exposes the value of the private field safely.
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius with validation.
     * Access modifier: PUBLIC
     * Why: Allows modifying the radius while enforcing validation rules (non-negative).
     * @param radius the new radius
     */
    public void setRadius(double radius) {
        // Task 9: Code broken into meaningful method
        if (isValidRadius(radius)) {
            this.radius = radius;
        } else {
            System.out.println("Error: Radius cannot be negative. Setting to 0.");
            this.radius = 0;
        }
    }

    /**
     * Calculates the total surface area of the sphere.
     * Task 11: Implemented surface area computation.
     * @return surface area
     */
    public double calculateSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculates the volume of the sphere.
     * Task 11: Implemented volume computation.
     * @return volume
     */
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Helper method to validate radius value.
     * Task 9: Extracted meaningful method.
     */
    private boolean isValidRadius(double value) {
        return value >= 0;
    }
}