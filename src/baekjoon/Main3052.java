package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main3052 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3052.txt"));

        Scanner sc = new Scanner(System.in);
        int[] count = new int[43];

        for (int i = 0; i < 10; i++) {
            int n = sc.nextInt();
            count[n % 42]++;
        }

        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
