package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2775 {

    public static int solve(int k, int n) {
        if (k == 0) {
            return n;
        } else if (n == 1) {
            return 1;
        }
        return solve(k, n - 1) + solve(k - 1, n);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2775.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            //k층 n호 = k층 n-1호 + k-1층 n호
            System.out.println(solve(k, n));
        }
    }
}
