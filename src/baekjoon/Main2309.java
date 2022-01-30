package baekjoon;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main2309 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2309.txt"));

        Scanner sc = new Scanner(System.in);
        int N = 9;
        int[] arr = new int[9];
        int total = 0;
        for (int i = 0; i < N; i++) {
            int cur = sc.nextInt();
            arr[i] = cur;
            total += cur;
        }
        int i = 0;
        int j = 0;
        boolean flag = false;
        for (; i < N - 1; i++) {
            for (j = i + 1; j < N; j++) {
                int n1 = arr[i];
                int n2 = arr[j];
                if (total - n1 - n2 == 100) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        int[] sorted = new int[7];
        int k = 0;
        for (int x : arr) {
            if (x != arr[i] && x != arr[j]) {
                sorted[k++] = x;
            }
        }
        Arrays.sort(sorted);
        for (int x : sorted) {
            System.out.println(x);
        }
    }
}
