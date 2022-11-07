package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2630 {

    static int N;
    static int[][] arr;
    static int[] answer;    // 0: 흰색, 1: 파란색

    static void solve(int i_start, int i_end, int j_start, int j_end) {

        int color = arr[i_start][j_start];

        // 1칸 일경우
        if (i_start + 1 == i_end) {
            answer[color]++;
            return;
        }

        for (int i = i_start; i < i_end; i++) {
            for (int j = j_start; j < j_end; j++) {

                // 범위 안에 색이 다를 경우 4등분하여 재귀
                if (arr[i][j] != color) {
                    int i_mid = (i_start + i_end) / 2;
                    int j_mid = (j_start + j_end) / 2;
                    solve(i_start, i_mid, j_start, j_mid);
                    solve(i_mid, i_end, j_start, j_mid);
                    solve(i_start, i_mid, j_mid, j_end);
                    solve(i_mid, i_end, j_mid, j_end);
                    return;
                }
            }
        }
        answer[color]++;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2630.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        answer = new int[2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, N, 0, N);

        System.out.println(answer[0] + "\n" + answer[1]);
    }
}
