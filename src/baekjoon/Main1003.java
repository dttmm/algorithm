package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1003 {
    static int[] dp;

    public static int f0(int x) {
        if (x == 0) {
            return 1;
        }
        if (x == 1) {
            return 0;
        }
        if (dp[x] != 0) return dp[x];
        return dp[x] = f0(x - 1) + f0(x - 2);
    }

    public static int f1(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        if (dp[x] != 0) return dp[x];
        return dp[x] = f1(x - 1) + f1(x - 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1003.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                System.out.println(1 + " " + 0);
            } else {
                dp = new int[N + 1];
                System.out.print(f0(N) + " ");

                dp = new int[N + 1];
                System.out.println(f1(N));
            }


        }
    }
}
