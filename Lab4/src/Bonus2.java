import java.util.Scanner;

public class Bonus2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        String result = removePalindromes(str);
        System.out.println("Result: " + result);

        sc.close();
    }

    public static String removePalindromes(String str) {
        String current = str;
        String previous;

        do {
            previous = current;
            current = removeOnePalindrome(current);
        } while (!current.equals(previous));

        return current;
    }

    public static String removeOnePalindrome(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 2; j <= str.length(); j++) {
                String substring = str.substring(i, j);
                if (substring.length() > 1 && isPalindrome(substring)) {
                    return str.substring(0, i) + str.substring(j);
                }
            }
        }
        return str;
    }

    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
