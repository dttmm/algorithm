package algorithm.day02array2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1209 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day02_1209.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int case_num = sc.nextInt();
            System.out.print("#" + case_num + " ");
            int N = 100;
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                int sum_h = 0;
                int sum_v = 0;
                for (int j = 0; j < N; j++) {
                    sum_h += arr[i][j];
                    sum_v += arr[j][i];
                }
                max = Math.max(max, Math.max(sum_h, sum_v));
            }

            int sum_rb = 0;
            int sum_lb = 0;
            for (int i = 0; i < N; i++) {
                sum_rb += arr[i][i];
                sum_lb += arr[N - 1 - i][N - 1 - i];
            }
            max = Math.max(max, Math.max(sum_rb, sum_lb));
            System.out.println(max);
        }
    }
}
