package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main14696 {

    public static void win(int[] arr, int i) {
        if (arr[i] > 0) {
            System.out.println("A");
        } else if (arr[i] < 0) {
            System.out.println("B");
        } else {
            if (i == 0) {
                System.out.println("D");
            } else {
                win(arr, i - 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14696.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for (int k = 0; k < N; k++) {
            int[] arr = new int[4];
            // A
            int x = sc.nextInt();
            for (int i = 0; i < x; i++) {
                int card = sc.nextInt();
                arr[card - 1]++;
            }
            // B
            x = sc.nextInt();
            for (int i = 0; i < x; i++) {
                int card = sc.nextInt();
                arr[card - 1]--;
            }
            win(arr, 3);
        }
    }
}
