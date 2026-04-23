public class Task2 {
    public static void main(String[] args) {
        int a, b, c;
        a = 10;
        b = 25;

        c = b % a;
        System.out.println("b % a = " + c);

        System.out.println("a % 3 = " + (a % 3));

        c = a * b;
        System.out.println("(a * b) % 120 = " + (c % 120));
    }
}