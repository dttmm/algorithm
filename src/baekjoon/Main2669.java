package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2669 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2669.txt"));

        Scanner sc = new Scanner(System.in);
        int N = 4;

        int[][] arr = new int[101][101];

        int count = 0;

        for (int k = 0; k < 4; k++) {
            int s_start = sc.nextInt();
            int y_start = sc.nextInt();
            int s_end = sc.nextInt();
            int y_end = sc.nextInt();
            for (int i = s_start; i < s_end; i++) {
                for (int j = y_start; j < y_end; j++) {
                    if (arr[i][j] == 0) {
                        arr[i][j] = 1;
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
}
