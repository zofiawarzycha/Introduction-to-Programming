import java.util.*;

public class bonus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = 30;
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || j == 0 || i == N-1 || j == N-1) {
                    map[i][j] = 'W';
                } else {
                    if (Math.random() < 0.3) {
                        map[i][j] = 'W';
                    } else {
                        map[i][j] = 'O';
                    }
                }
            }
        }

        int px = 1;
        int py = 1;
        map[px][py] = 'O';

        int ex = N-2;
        int ey = N-2;
        map[ex][ey] = 'E';

        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == px && j == py) {
                        System.out.print('P');
                    } else {
                        System.out.print(map[i][j]);
                    }
                }
                System.out.println();
            }

            if (px == ex && py == ey) {
                System.out.println("Wygrałaś! Dotarłaś na pole E");
                break;
            }

            System.out.println("Podaj ruch (L R U D):");
            String move = sc.next().toUpperCase();
            int npx = px;
            int npy = py;
            if (move.equals("L")) {
                npy = py - 1;
            } else if (move.equals("R")) {
                npy = py + 1;
            } else if (move.equals("U")) {
                npx = px - 1;
            } else if (move.equals("D")) {
                npx = px + 1;
            } else {
                System.out.println("Nieprawidłowy ruch.");
                continue;
            }

            if (npx < 0 || npx >= N || npy < 0 || npy >= N || map[npx][npy] == 'W') {
                System.out.println("Nie można tam wejść!");
                continue;
            }

            px = npx;
            py = npy;
        }
    }
}
