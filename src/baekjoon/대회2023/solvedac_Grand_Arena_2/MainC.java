package baekjoon.대회2023.solvedac_Grand_Arena_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainC {

    static int N;
    static int[] arr;
    static int max;
    static int[][] d;

    static int solve(int n) {

        int min = 1000000000;
        for (int i = 0; i < N; i++) {
            long m = arr[i];

            while (m <= n) {
                m *= 2;
            }
            m /= 2;

            min = (int) Math.min(min, m);
        }

        int diff = n - min;
        return diff;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_2/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        max = 1;
        d = new int[N][32];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            arr[i] = n;
            max = Math.max(max, n);
        }

        int answer = 1000000000;
        for (int i = 0; i < N; i++) {
            int n = arr[i];

            while (n < max) n *= 2;

            int result = solve(n);
            answer = Math.min(answer, result);
        }

        System.out.println(answer);
    }
}
