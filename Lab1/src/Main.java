void main() {
    int a = 12;
    int b = 4;
    add(a, b);
    difference(a, b);
    product(a, b);
    modulo(a, b);
    ratio(a, b);
    multiplicationTable(a);
}

void add(int a, int b) {
    System.out.println("The sum is: " + Integer.toString(a+b));
}

void difference(int a, int b) {
    System.out.println("The difference is: " + Integer.toString(a-b));
}

void product(int a, int b) {
    System.out.println("The product is: " + Integer.toString(a*b));
}

void modulo(int a, int b) {
    System.out.println("The modulo is: " + Integer.toString(a % b));
}

void ratio(int a, int b) {
    double result = (double) a / b;
    System.out.println("The ratio is: " + result);
}

void multiplicationTable(int a) {
    System.out.println("The multiplication table for a=" + Integer.toString(a) + " is: ");
    for (int i = 1; i <= 10; i++) {
        System.out.println(a + " x " + i + " = " + (a*i));
    }
}