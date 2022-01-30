package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2605 {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2605.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            for (int j = 0; j < num; j++) {
                swap(arr, i - j, i - 1 - j);
            }
            arr[i - num] = i + 1;
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}
