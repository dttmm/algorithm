package baekjoon;

import java.io.*;

public class Main1992 {

    static int N;
    static int[][] arr;
    static BufferedWriter bw;

    static void solve(int start_i, int end_i, int start_j, int end_j) throws IOException {

        int init = arr[start_i][start_j];

        // 칸이 한 칸인 경우
        if (end_i - start_i == 1) {
            bw.write(init + "");
            return;
        }

        for (int i = start_i; i < end_i; i++) {
            for (int j = start_j; j < end_j; j++) {

                // 다른 숫자가 있는 경우 4등분하여 재귀
                if (arr[i][j] != init) {
                    bw.write('(');

                    int mid_i = (start_i + end_i) / 2;
                    int mid_j = (start_j + end_j) / 2;

                    solve(start_i, mid_i, start_j, mid_j);
                    solve(start_i, mid_i, mid_j, end_j);
                    solve(mid_i, end_i, start_j, mid_j);
                    solve(mid_i, end_i, mid_j, end_j);

                    bw.write(')');
                    return;
                }
            }
        }
        bw.write(init + "");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1992.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                int n = c - '0';
                arr[i][j] = n;
            }
        }

        solve(0, N, 0, N);

        bw.flush();
        bw.close();
    }
}
