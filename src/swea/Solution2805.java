package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution2805 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/2805.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }

            int sum = 0;

            for (int i = 0; i <= N / 2; i++) {
                for (int j = N / 2 - i; j <= N / 2 + i; j++) {
                    sum += arr[i][j];
                }
            }
            for (int i = N - 1; i > N / 2; i--) {
                for (int j = N / 2 - (N - 1 - i); j <= N / 2 + (N - 1 - i); j++) {
                    sum += arr[i][j];

                }
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }
}