package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution7236 {
    static int N;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/7236.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            char[][] arr = new char[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = st.nextToken().charAt(0);
                }
            }

            int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 'W') {
                        int count = 0;
                        for (int dir = 0; dir < 8; dir++) {
                            int newI = i + di[dir];
                            int newJ = j + dj[dir];
                            if (isIn(newI, newJ) && arr[newI][newJ] == 'W') {
                                count++;
                            }
                        }
                        max = Math.max(count, max);
                    }
                }
            }

            if (max == 0) max = 1;
            System.out.println("#" + test_case + " " + max);
        }
    }
}