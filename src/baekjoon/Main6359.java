package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main6359 {
    static int N;
    static boolean arr[];

    public static void f(int n) {
        if (n == 0) return;

        for (int i = n; i <= N; i += n) {
            arr[i] = !arr[i];
        }

        f(n - 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6359.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            arr = new boolean[N + 1];

            f(N);

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (arr[i]) count++;
            }

            System.out.println(count);
        }
    }
}
