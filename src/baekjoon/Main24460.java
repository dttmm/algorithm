package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main24460 {
    static int N;
    static long[][] arr;

    public static long solve(int n, int start_i, int start_j) {
        if (n == 1) {
            return arr[0][0];
        }
        if (n == 2) {
            long[] brr = new long[4];
            int x = 0;
            for (int i = start_i; i <= start_i + 1; i++) {
                for (int j = start_j; j <= start_j + 1; j++) {
                    brr[x] = arr[i][j];
                    x++;
                }
            }
            Arrays.sort(brr);
            return brr[1];
        } else {
            int mid = n / 2;
            long[] brr = new long[4];
            int x = 0;
            brr[x++] = solve(mid, start_i, start_j);
            brr[x++] = solve(mid, start_i, start_j + mid);
            brr[x++] = solve(mid, start_i + mid, start_j);
            brr[x++] = solve(mid, start_i + mid, start_j + mid);
            Arrays.sort(brr);
            return brr[1];
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/24460.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(N, 0, 0));
    }
}
