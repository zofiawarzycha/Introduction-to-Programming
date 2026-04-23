import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        String compressed = compressString(str);
        System.out.println("Compressed: " + compressed);

        sc.close();
    }

    public static String compressString(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            int count = 1;

            while (i + 1 < str.length() && str.charAt(i + 1) == current) {
                count++;
                i++;
            }

            result.append(count).append(current);
        }

        return result.toString();
    }
}
