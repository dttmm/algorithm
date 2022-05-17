package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main9184 {
    static int[][][] D = new int[21][21][21];
    ;

    public static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (a > 20 || b > 20 || c > 20) {
            if (D[20][20][20] != 0) return D[20][20][20];
            return D[20][20][20] = w(20, 20, 20);
        } else if (a < b && b < c) {
            if (D[a][b][c] != 0) return D[a][b][c];
            return D[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
        } else {
            if (D[a][b][c] != 0) return D[a][b][c];
            return D[a][b][c] = w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9184.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1 && c == -1) return;

            int result = w(a, b, c);

            System.out.printf("w(%d, %d, %d) = %d", a, b, c, result);
            System.out.println();
        }
    }
}
