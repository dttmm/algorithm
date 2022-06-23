package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6785 {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {0, 0, 1, 0};

    public static boolean isIn(int n, int newX, int newY) {
        int max = (int) Math.pow(5, n);
        if (newX >= 0 && newX < max && newY >= 0 && newY < max) return true;
        return false;
    }

    public static boolean isCrystal(int x_mid, int y_mid, int x, int y) {
        boolean flag = false;
        for (int dir = 0; dir < 4; dir++) {
            int newX = x_mid + dx[dir];
            int newY = y_mid + dy[dir];
            if (x == newX && y == newY) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static boolean solve(int n, int x, int y) {

        int x_parent = x / 5;
        int y_parent = (y / 5) - 1;
        int x_mid = x_parent * 5 + 2;
        int y_mid = (y_parent + 1) * 5;

        int x_pre_parent = x / 5;
        int y_pre_parent = y / 5;
        int x_pre_mid = (n - 2) * 5 + 2;
        int y_pre_mid = (n - 2) * 5;

        boolean result = false;

        if (n == 1) {
            if (isCrystal(x_mid, y_mid, x, y)) {
                return true;
            } else {
                return false;
            }
        } else if (n == 2) {

            if (isCrystal(x_pre_mid, y_pre_mid, x_pre_parent, y_pre_parent)) {
                result = solve(n - 1, x_pre_parent, y_pre_parent);

            } else if (isCrystal(x_mid, y_mid, x, y)) {
                result = solve(n - 1, x_parent, y_parent);
            }

        } else {
            result = solve(n - 1, x_pre_parent, y_pre_parent);
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6785.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            boolean result = solve(M, x, y);
            if (result) {
                System.out.println("crystal");
            } else {
                System.out.println("empty");
            }
        }
    }
}
