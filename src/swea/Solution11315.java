package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution11315 {
    static int N;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/11315.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            char[][] arr = new char[N][N];
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = s.charAt(j);
                }
            }

            int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
            String answer = "NO";

            boolean flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 'o') {
                        for (int dir = 0; dir < 8; dir++) {
                            int count = 1;
                            int newI = i + di[dir];
                            int newJ = j + dj[dir];
                            while (isIn(newI, newJ) && arr[newI][newJ] == 'o') {
                                count++;
                                newI += di[dir];
                                newJ += dj[dir];
                                if (count == 5) {
                                    answer = "YES";
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag) break;
                        }
                    }
                    if (flag) break;
                }
                if (flag) break;
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }
}