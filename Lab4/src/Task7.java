import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();
        System.out.print("Enter a substring to find: ");
        String substring = sc.nextLine();

        int count = countSubstring(str, substring);
        System.out.println("The substring \"" + substring + "\" appears " + count + " times.");

        sc.close();
    }

    public static int countSubstring(String str, String substring) {
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
