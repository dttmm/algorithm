package algorithm.day10problem1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간 35분
public class Solution6109 {
    static int[][] arr;
    static int N;
    // up down right left
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static int dir;
    static int newX;
    static int newY;
    static int x;
    static int y;
    static boolean[][] used;

    public static boolean isIn() {
        if (newX >= 0 && newX < N && newY >= 0 && newY < N) return true;
        return false;
    }

    public static void move(int i, int j) {
        y = i;
        x = j;
        newY = y + dy[dir];
        newX = x + dx[dir];
        while (isIn() && arr[newY][newX] == 0) {
            arr[newY][newX] = arr[y][x];
            arr[y][x] = 0;
            y = newY;
            x = newX;
            newY = y + dy[dir];
            newX = x + dx[dir];
        }
        if (isIn() && arr[newY][newX] == arr[y][x] && !used[newY][newX]) {
            arr[newY][newX] *= 2;
            arr[y][x] = 0;
            used[newY][newX] = true;
        }
    }

    public static void solve(String d) {
        if (d.equals("up")) {
            dir = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    move(i, j);
                }
            }
        } else if (d.equals("down")) {
            dir = 1;
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < N; j++) {
                    move(i, j);
                }
            }
        } else if (d.equals("right")) {
            dir = 2;
            for (int j = N - 1; j >= 0; j--) {
                for (int i = 0; i < N; i++) {
                    move(i, j);
                }
            }
        } else {
            dir = 3;
            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    move(i, j);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day10_6109.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            String d = st.nextToken();

            x = 0;
            y = 0;
            newX = 0;
            newY = 0;
            used = new boolean[N][N];

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            solve(d);
            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}