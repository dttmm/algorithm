package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main7113 {
    static int N;
    static int M;
    static int total;

    public static void solve(int n, int m) {

        if (n == 0 || m == 0) return;

        int max = Math.max(n, m);
        int min = Math.min(n, m);

        if (max == min) {
            total++;
            return;
        }

        total += max / min;
        solve(max % min, min);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7113.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        total = 0;

        solve(N, M);

        System.out.println(total);
    }
}
