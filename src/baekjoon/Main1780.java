package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1780 {

    static int N;
    static int[][] arr;
    static int[] count; // 0번 인덱스: -1개수, 1번 인덱스: 0개수, 2번 인덱스: 1개수

    static void solve(int start_i, int end_i, int start_j, int end_j) {
        int init = arr[start_i][start_j];

        // 1칸인 경우
        if (end_i - start_i == 1) {
            count[init + 1]++;
            return;
        }

        for (int i = start_i; i < end_i; i++) {
            for (int j = start_j; j < end_j; j++) {
                int n = arr[i][j];
                if (n != init) { // 다른 숫자가 있으면 9등분 하여 재귀
                    int mid_i_1 = start_i + (end_i - start_i) / 3;
                    int mid_i_2 = start_i + (end_i - start_i) * 2 / 3;
                    int mid_j_1 = start_j + (end_j - start_j) / 3;
                    int mid_j_2 = start_j + (end_j - start_j) * 2 / 3;

                    solve(start_i, mid_i_1, start_j, mid_j_1);
                    solve(start_i, mid_i_1, mid_j_1, mid_j_2);
                    solve(start_i, mid_i_1, mid_j_2, end_j);

                    solve(mid_i_1, mid_i_2, start_j, mid_j_1);
                    solve(mid_i_1, mid_i_2, mid_j_1, mid_j_2);
                    solve(mid_i_1, mid_i_2, mid_j_2, end_j);

                    solve(mid_i_2, end_i, start_j, mid_j_1);
                    solve(mid_i_2, end_i, mid_j_1, mid_j_2);
                    solve(mid_i_2, end_i, mid_j_2, end_j);

                    return;
                }
            }
        }

        count[init + 1]++;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1780.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        count = new int[3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        solve(0, N, 0, N);
        System.out.println(count[0] + "\n" + count[1] + "\n" + count[2]);
    }
}
