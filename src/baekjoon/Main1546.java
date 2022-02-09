package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main1546 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1546.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            arr[i] = n;
            max = n > max ? n : max;
        }

        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += ((double) arr[i] / max) * 100;
        }

        System.out.println(sum / N);
    }
}
