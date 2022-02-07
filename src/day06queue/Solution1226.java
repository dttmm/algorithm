package day06queue;

import java.io.FileInputStream;
import java.util.Scanner;

// 17분
public class Solution1226 {
    static int[][] arr;
    static int[] delX;
    static int[] delY;

    public static boolean find(int i, int j) {
        int newX;
        int newY;
        if (arr[i][j] == 3) {
            return true;
        }
        if (arr[i][j] != 1) {
            arr[i][j] = 1;
            for (int dir = 0; dir < 4; dir++) {
                newX = j + delX[dir];
                newY = i + delY[dir];
                if (arr[newY][newX] != 1) {
                    if (find(newY, newX)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day06_1226.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int case_num = sc.nextInt();
            int N = 16;
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }
            delX = new int[]{1, -1, 0, 0};
            delY = new int[]{0, 0, 1, -1};

            if (find(1, 1)) {
                System.out.println("#" + case_num + " " + 1);
            } else {
                System.out.println("#" + case_num + " " + 0);
            }
        }
    }
}