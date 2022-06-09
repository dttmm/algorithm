package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main5956 {
    public static int total;

    public static void f(int N, int M, int k) {
        if (N % 2 == 0 || M % 2 == 0) return;

        total += (int) Math.pow(4, k);
        f(N / 2, M / 2, k + 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5956.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        total = 0;

        f(N, M, 0);
        System.out.println(total);
    }
}
