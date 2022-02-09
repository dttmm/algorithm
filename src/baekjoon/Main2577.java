package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2577 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2577.txt"));

        Scanner sc = new Scanner(System.in);

        int[] arr = new int[10];

        int total = 1;
        for (int i = 0; i < 3; i++) {
            total *= sc.nextInt();
        }

        while (total != 0) {
            arr[total % 10]++;
            total /= 10;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(arr[i]);
        }
    }
}
