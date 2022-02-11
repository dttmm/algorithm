package algorithm.day10problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 46분
public class Solution1861 {
    static int[][] arr;
    static int N;
    static int max;
    static int max_num;
    // 위 아래 오른쪽 왼쪽
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static boolean isIn(int newY, int newX) {
        if (newY >= 0 && newY < N && newX >= 0 && newX < N) return true;
        return false;
    }

    public static int move(int y, int x) {
        int newY;
        int newX;
        int count = 0;
        for (int dir = 0; dir < 4; dir++) {
            newY = y + dy[dir];
            newX = x + dx[dir];
            if (isIn(newY, newX) && arr[newY][newX] - arr[y][x] == 1) {
                count++;
                count += move(newY, newX);
            }
        }
        return count;
    }

    public static void solve() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int count = move(i, j);
                if (count > max) {
                    max = count;
                    max_num = arr[i][j];
                } else if (count == max && arr[i][j] < max_num) {
                    max_num = arr[i][j];
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day10_1861.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            max = 0;
            max_num = 0;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve();
            max += 1;
            System.out.println("#" + test_case + " " + max_num + " " + max );
        }
    }
}