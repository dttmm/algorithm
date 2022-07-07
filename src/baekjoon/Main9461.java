package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main9461 {
    static long[] D;

    public static long solve(int n) {
        if (D[n] != 0) return D[n];
        if (n <= 3) return 1;
        if (n == 4 || n == 5) return 2;
        return D[n] = solve(n - 1) + solve(n - 5);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9461.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        D = new long[101];
        D[1] = 1;
        D[2] = 1;
        D[3] = 1;
        D[4] = 2;
        D[5] = 2;


        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            System.out.println(solve(N));
        }
    }
}
