package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1890 {
    static int N;
    static int[][] arr;
    static long[][] visitied;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void solve(int i, int j, long count) {
        if (i == N - 1 && j == N - 1) return;

        int newI = i + arr[i][j];
        int newJ = j + arr[i][j];

        // 아래쪽으로 갔을 때
        if (isIn(newI, j)) {
            visitied[newI][j] += count;
        }

        // 오른쪽으로 갔을 때
        if (isIn(i, newJ)) {
            visitied[i][newJ] += count;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1890.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        visitied = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visitied[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visitied[i][j] > 0) solve(i, j, visitied[i][j]);
            }
        }

        System.out.println(visitied[N - 1][N - 1]);
    }
}
