package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;
// 1시간 26분
public class Main2477 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2477.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[2][6];

        int x_max = 0;
        int y_max = 0;

        for (int i = 0; i < 6; i++) {
            int dir = sc.nextInt();
            int len = sc.nextInt();

            arr[0][i] = dir;
            arr[1][i] = len;

            if (dir == 1 || dir == 2) {
                x_max = len > x_max ? len : x_max;
            } else {
                y_max = len > y_max ? len : y_max;
            }
        }

        int xIn = 0;
        int yIn = 0;
        for (int i = 0; i < 6; i++) {
            int pre = 0;
            int next = 0;
            if (i == 0) {
                pre = 5;
                next = i + 1;
            } else if (i == 5) {
                pre = i - 1;
                next = 0;
            } else {
                pre = i - 1;
                next = i + 1;
            }
            if (arr[0][i] == 1 || arr[0][i] == 2) {
                if (arr[1][pre] + arr[1][next] == y_max) {
                    xIn = arr[1][i];
                }
            } else {
                if (arr[1][pre] + arr[1][next] == x_max) {
                    yIn = arr[1][i];
                }
            }
        }
        System.out.println((x_max * y_max - xIn * yIn) * N);
    }
}
