package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2096 {

    static int N;
    static int[][] arrMax;
    static int[][] arrMin;
    static int[] dj = {-1, 0, 1};

    static boolean isIn(int j) {
        if (j >= 0 && j < 3) return true;
        return false;
    }

    static void solve(int i) {
        if (i == N - 1) return;

        for (int j = 0; j < 3; j++) {

            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int dir = 0; dir < 3; dir++) {
                int newJ = j + dj[dir];

                if (isIn(newJ)) {
                    max = Math.max(max, arrMax[i][newJ]);
                    min = Math.min(min, arrMin[i][newJ]);
                }
            }

            arrMax[i + 1][j] += max;
            arrMin[i + 1][j] += min;
        }

        solve(i + 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2096.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arrMax = new int[N][3];
        arrMin = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int n = Integer.parseInt(st.nextToken());
                arrMax[i][j] = n;
                arrMin[i][j] = n;
            }
        }

        solve(0);

        int max = Math.max(Math.max(arrMax[N - 1][0], arrMax[N - 1][1]), arrMax[N - 1][2]);
        int min = Math.min(Math.min(arrMin[N - 1][0], arrMin[N - 1][1]), arrMin[N - 1][2]);

        System.out.println(max + " " + min);
    }
}
