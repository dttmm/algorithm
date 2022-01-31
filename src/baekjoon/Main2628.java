package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2628 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2628.txt"));

        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();

        int[] xArr = new int[x + 1];
        int[] yArr = new int[y + 1];
        xArr[x] = 1;
        yArr[y] = 1;

        int N = sc.nextInt();

        for (int k = 0; k < N; k++) {
            int dir = sc.nextInt();
            int pos = sc.nextInt();
            if (dir == 0) {
                xArr[pos] = 1;
            } else {
                yArr[pos] = 1;
            }
        }
        int max = 0;
        int pre_x = 0;
        for (int i = 0; i <= x; i++) {
            if (xArr[i] == 1) {
                int x_len = i - pre_x;
                pre_x = i;
                int pre_y = 0;
                for (int j = 0; j <= y; j++) {
                    if (yArr[j] == 1) {
                        int y_len = j - pre_y;
                        int area = x_len * y_len;
                        max = area > max ? area : max;
                        pre_y = j;
                    }
                }
            }
        }

        System.out.println(max);
    }
}
