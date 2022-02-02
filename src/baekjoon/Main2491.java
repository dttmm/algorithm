package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

// 19분
public class Main2491 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2491.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        int max = Integer.MIN_VALUE;
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                if (arr[i] >= arr[i - 1]) {
                    count++;
                } else {
                    count = 1;
                }
            }
            max = count > max ? count : max;
        }

        count = 1;
        for (int i = 0; i < N; i++) {
            if (i > 0) {
                if (arr[i] <= arr[i - 1]) {
                    count++;
                } else {
                    count = 1;
                }
            }
            max = count > max ? count : max;
        }

        System.out.println(max);
    }
}