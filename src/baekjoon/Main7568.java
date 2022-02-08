package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main7568 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7568.txt"));

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[][] arr = new int[2][N];
        for (int i = 0; i < N; i++) {
            arr[0][i] = sc.nextInt();
            arr[1][i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            int rank = 1;
            for (int j = 0; j < N; j++) {
                if (arr[0][i] < arr[0][j] && arr[1][i] < arr[1][j]) {
                    rank++;
                }
            }
            System.out.print(rank + " ");
        }
    }
}
