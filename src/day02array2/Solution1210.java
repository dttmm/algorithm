package day02array2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1210 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day02_1210.txt"));

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

            int x_idx = 0;
            for (int i = 0; i < N; i++) {
                if (arr[N - 1][i] == 2) {
                    x_idx = i;
                    break;
                }
            }

            for (int i = N - 1; i >= 0; i--) {
                // 왼쪽 검사
                if (x_idx > 0 && arr[i][x_idx - 1] == 1) {

                    while (x_idx - 1 >= 0) {
                        x_idx--;
                        if ((x_idx <= 0) || (arr[i][x_idx - 1] == 0)) {
                            break;
                        }
                    }
                }
                // 오름쪽 검사
                else if (x_idx < N - 1 && arr[i][x_idx + 1] == 1) {
                    while (x_idx + 1 <= N - 1) {
                        x_idx++;
                        if ((x_idx >= N - 1) || (arr[i][x_idx + 1] == 0)) {
                            break;
                        }
                    }
                }
            }
            System.out.println(x_idx);
        }
    }
}
