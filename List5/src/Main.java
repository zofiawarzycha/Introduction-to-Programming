import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        passwordCheck(scanner);
        String sentence = scanner.nextLine();
        punctuationStats(sentence);
        gamblerRuin();
        singlePointCrossover(scanner);
        tokenLengthDist(scanner);
        decimalToBinary(scanner);
        scanner.close();
    }

    public static void passwordCheck(Scanner scanner) {
        String password = "qwerty";
        int tries = 0;
        while (tries < 5) {
            String in = scanner.nextLine();
            if (in.equals(password)) {
                System.out.println("Access granted!");
                return;
            }
            System.out.println("Access denied!");
            tries++;
        }
    }

    public static void punctuationStats(String s) {
        int[] c = new int[7];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '.') c[0]++;
            else if (ch == ',') c[1]++;
            else if (ch == '?') c[2]++;
            else if (ch == '!') c[3]++;
            else if (ch == ':') c[4]++;
            else if (ch == ';') c[5]++;
            else c[6]++;
        }
        System.out.println("Sentence =" + s);
        System.out.println("Sentence stats:");
        System.out.println("period = " + c[0]);
        System.out.println("comma = " + c[1]);
        System.out.println("question mark = " + c[2]);
        System.out.println("exclamation = " + c[3]);
        System.out.println("colon = " + c[4]);
        System.out.println("semicolon = " + c[5]);
        System.out.println("other = " + c[6]);
    }

    public static void gamblerRuin() {
        int start = 500;
        int target = 2500;
        int times = 1000;
        int win = 0;
        int bets = 0;
        Random rand = new Random();
        for (int i = 0; i < times; i++) {
            int money = start;
            int b = 0;
            while (money > 0 && money < target) {
                b++;
                if (rand.nextBoolean()) money++;
                else money--;
            }
            if (money >= target) win++;
            bets += b;
        }
        System.out.println("Starting $" + start + " to $" + target);
        System.out.println("Wins: " + win + "/" + times);
        System.out.println("Odds: " + (double)win/times);
        if (win > 0) System.out.println("Avg bets if win: " + (double)bets/win);
        else System.out.println("Avg bets if win: n/a");
    }

    public static void singlePointCrossover(Scanner scanner) {
        Random rand = new Random();
        int n = scanner.nextInt();
        scanner.nextLine();
        String a = "", b = "";
        for (int i = 0; i < n; i++) {
            a += rand.nextBoolean() ? "1" : "0";
            b += rand.nextBoolean() ? "1" : "0";
        }
        int p = rand.nextInt(n-1) + 1;
        String o1 = a.substring(0, p) + b.substring(p);
        String o2 = b.substring(0, p) + a.substring(p);
        System.out.println("Chromosome1: " + a.substring(0,p) + "|" + a.substring(p));
        System.out.println("Chromosome2: " + b.substring(0,p) + "|" + b.substring(p));
        System.out.println("Offspring1:  " + o1.substring(0,p) + "|" + o1.substring(p));
        System.out.println("Offspring2:  " + o2.substring(0,p) + "|" + o2.substring(p));
        System.out.println("Crossover point: " + p);
    }

    public static void tokenLengthDist(Scanner scanner) {
        int[] dist = new int[17];
        while (scanner.hasNext()) {
            String t = scanner.next();
            if (t.equals("END")) break;
            int l = t.length();
            if (l > 15) l = 16;
            dist[l]++;
        }
        for (int i = 1; i <= 16; i++)
            System.out.println(i + ":" + dist[i]);
    }

    public static void decimalToBinary(Scanner scanner) {
        int x = scanner.nextInt();
        String bin = "";
        int y = x;
        if (y == 0) bin = "0";
        while (y > 0) {
            bin = (y % 2) + bin;
            y = y / 2;
        }
        System.out.println(x + " in binary: " + bin);
    }
}
