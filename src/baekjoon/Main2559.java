package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

//17 분
public class Main2559 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2559.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= N - K; i++) {
            int sum = 0;
            for (int j = 0; j < K; j++) {
                sum += arr[i + j];
            }
            max = sum > max ? sum : max;
        }
        System.out.println(max);
    }
}