package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2563 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2563.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[100][100];

        int count = 0;
        for (int k = 0; k < N; k++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
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
