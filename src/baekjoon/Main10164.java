package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10164 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10164.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];

        int result = 0;
        if (x != 0) {
            int x_i = (x - 1) / M;
            int x_j = (x - 1) % M;

            for (int i = 0; i <= x_i; i++) {
                arr[i][0] = 1;
            }
            for (int i = 0; i <= x_j; i++) {
                arr[0][i] = 1;
            }
            for (int i = 1; i <= x_i; i++) {
                for (int j = 1; j <= x_j; j++) {
                    arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
                }
            }

            for (int i = x_i + 1; i < N; i++) {
                arr[i][x_j] = 1;
            }
            for (int i = x_j + 1; i < M; i++) {
                arr[x_i][i] = 1;
            }
            for (int i = x_i + 1; i < N; i++) {
                for (int j = x_j + 1; j < M; j++) {
                    arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
                }
            }

            result = arr[x_i][x_j] * arr[N - 1][M - 1];
        } else {
            for (int i = 0; i < N; i++) {
                arr[i][0] = 1;
            }
            for (int i = 0; i < M; i++) {
                arr[0][i] = 1;
            }
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < M; j++) {
                    arr[i][j] = arr[i][j - 1] + arr[i - 1][j];
                }
            }
            result = arr[N - 1][M - 1];
        }
        System.out.println(result);
    }
}
