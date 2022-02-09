package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2562 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2562.txt"));

        Scanner sc = new Scanner(System.in);

        int max = Integer.MIN_VALUE;
        int max_index = 0;
        for (int i = 0; i < 9; i++) {
            int n = sc.nextInt();
            if (n > max) {
                max = n;
                max_index = i + 1;
            }
        }
        System.out.println(max);
        System.out.println(max_index);
    }
}
