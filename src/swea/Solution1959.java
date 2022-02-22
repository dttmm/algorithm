package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1959 {

    public static int solve(int[] arr, int[] brr, int N, int M) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= M - N; i++) {
            int sum = 0;
            for (int j = 0; j < N; j++) {
                sum += arr[j] * brr[j + i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1959.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] brr = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                brr[i] = Integer.parseInt(st.nextToken());
            }
            int max;
            if (N > M) {
                max = solve(brr, arr, M, N);
            } else {
                max = solve(arr, brr, N, M);
            }
            System.out.println("#" + test_case + " " + max);
        }
    }
}