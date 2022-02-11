package algorithm.day10problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간 17분
public class Solution1949 {
    static int N;
    static int[][] arr;
    static int high;
    // 위 아래 오른 왼
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int max;

    public static boolean isIn(int newY, int newX) {
        if (newY >= 0 && newY < N && newX >= 0 && newX < N) return true;
        return false;
    }

    public static int move(int y, int x) {
        int newY;
        int newX;
        int max_count = 0;
        for (int dir = 0; dir < 4; dir++) {
            int count = 0;
            newY = y + dy[dir];
            newX = x + dx[dir];
            if (isIn(newY, newX)) {
                if (arr[newY][newX] < arr[y][x]) {
                    count++;
                    count += move(newY, newX);
                }
                max_count = Math.max(count, max_count);
            }
        }
        return max_count;
    }

    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == high) {
                    int count = move(i, j);
                    max = Math.max(count, max);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day10_1949.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            high = 0;
            max = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    arr[i][j] = n;
                    high = n > high ? n : high;
                }
            }

            solve();
            while (K > 0) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        int temp = arr[i][j];
                        arr[i][j] -= K;
                        if (arr[i][j] < 0) arr[i][j] = 0;
                        solve();
                        arr[i][j] = temp;

                    }
                }
                K--;
            }

            max += 1;
            System.out.println("#" + test_case + " " + max);
        }
    }
}