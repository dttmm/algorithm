package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main29719 {

    static final int MOD = 1000000007;
    static int N;
    static int M;

    static long solve(int x, int n) {
        if (n == 1) return x;

        long half = solve(x, n / 2);

        if (n % 2 == 0) return (half * half) % MOD;
        else return (((half * half) % MOD) * x) % MOD;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29719.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long a = solve(M, N);
        long b = solve(M - 1, N);

        System.out.println(a - b);
    }
}
