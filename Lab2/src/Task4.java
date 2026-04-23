public class Task4 {
    public static void main(String[] args) {
        double[] numbers = new double[5];
        double sum = 0;

        for (int i = 0; i < 5; i++) {
            numbers[i] = Math.random();
            System.out.println("Number " + (i + 1) + ": " + numbers[i]);
            sum += numbers[i];
        }

        double min = numbers[0];
        double max = numbers[0];
        for (int i = 1; i < 5; i++) {
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);
        }

        double average = sum / 5.0;

        System.out.println("Average: " + average);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
    }
}

