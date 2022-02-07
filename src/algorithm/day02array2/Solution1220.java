package algorithm.day02array2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1220 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day02_1220.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int tsum = 0;

            for (int j = 0; j < N; j++) {
                int top = 0;
                int bottom = 99;
                int sum = 0;

                for (int i = 0; i < N; i++) {
                    if (arr[i][j] == 1) {
                        top = i;
                        break;
                    }
                }
                for (int i = 0; i < N; i++) {
                    if (arr[99 - i][j] == 2) {
                        bottom = 99 - i;
                        break;
                    }
                }

                int pre = 1;
                for (int i = top; i <= bottom; i++) {
                    if (pre == 1 && arr[i][j] == 2) {
                        sum += 1;
                        pre = 2;
                    } else if (pre == 2 && arr[i][j] == 1) {
                        pre = 1;
                    }
                }
                tsum += sum;
            }

            System.out.println("#" + test_case + " " + tsum);
        }
    }
}
