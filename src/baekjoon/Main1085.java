package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

// 3분
public class Main1085 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1085.txt"));

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();

        int min_v = Integer.MAX_VALUE;
        int min_h = Integer.MAX_VALUE;

        min_v = Math.min(x, w - x);
        min_h = Math.min(y, h - y);

        System.out.println(Math.min(min_h, min_v));
    }
}
