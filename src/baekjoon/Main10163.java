package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main10163 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10163.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[1001][1001];
        int[] count = new int[N];
        for (int k = 0; k < N; k++) {
            int s_start = sc.nextInt();
            int y_start = sc.nextInt();
            int width = sc.nextInt();
            int height = sc.nextInt();
            for (int i = s_start; i < s_start + width; i++) {
                for (int j = y_start; j < y_start + height; j++) {
                    arr[i][j] = k + 1;
                }
            }
        }

        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                if (arr[i][j] != 0) {
                    count[arr[i][j] - 1]++;
                }
            }
        }

        for (int i : count) {
            System.out.println(i);
        }
    }
}
