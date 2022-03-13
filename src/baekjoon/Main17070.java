package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17070 {
    static int N;
    static int[][] arr;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    // 오른0 대각1 아래2
    public static int solve(int i, int j, int dir) {
        if (i == N - 1 && j == N - 1) return 1;
        int count = 0;
        // 오른쪽일때
        if (dir == 0) {
            // 오른쪽검사
            int newI = i;
            int newJ = j + 1;
            if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                count += solve(newI, newJ, 0);

                // 아래쪽검사
                newI = i + 1;
                newJ = j;
                if (isIn(newI, newJ) && arr[newI][newJ] == 0) {

                    // 대각선검사
                    newI = i + 1;
                    newJ = j + 1;
                    if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                        count += solve(newI, newJ, 1);
                    }
                }
            }
        }
        // 대각선일때
        else if (dir == 1) {
            int flag = 0;
            // 오른쪽검사
            int newI = i;
            int newJ = j + 1;
            if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                count += solve(newI, newJ, 0);
                flag++;
            }
            // 아래쪽검사
            newI = i + 1;
            newJ = j;
            if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                count += solve(newI, newJ, 2);
                flag++;
            }
            // 대각선검사
            newI = i + 1;
            newJ = j + 1;
            if (flag == 2 && isIn(newI, newJ) && arr[newI][newJ] == 0) {
                count += solve(newI, newJ, 1);
            }
        }
        // 아래쪽일때
        else {
            // 아래쪽검사
            int newI = i + 1;
            int newJ = j;
            if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                count += solve(newI, newJ, 2);

                // 오른쪽검사
                newI = i;
                newJ = j + 1;
                if (isIn(newI, newJ) && arr[newI][newJ] == 0) {

                    // 대각선검사
                    newI = i + 1;
                    newJ = j + 1;
                    if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                        count += solve(newI, newJ, 1);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17070.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int total = solve(0, 1, 0);
        System.out.println(total);
    }
}
