package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main10818 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10818.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            max = n > max ? n : max;
            min = n < min ? n : min;
        }
        System.out.println(min + " " + max);
    }
}
