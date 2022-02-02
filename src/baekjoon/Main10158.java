package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;
// 2시간
public class Main10158 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10158.txt"));
        Scanner sc = new Scanner(System.in);

        int w = sc.nextInt();
        int h = sc.nextInt();

        int x = sc.nextInt();
        int y = sc.nextInt();

        int t = sc.nextInt();

        x = (x + t) % (2 * w);
        if (x > w) {
            x = w - (x - w);
        }

        y = (y + t) % (2 * h);
        if (y > h) {
            y = h - (y - h);
        }
        System.out.println(x + " " + y);
    }
}
