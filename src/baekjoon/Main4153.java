package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main4153 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4153.txt"));

        Scanner sc = new Scanner(System.in);
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (a == 0 && b == 0 && c == 0) {
                break;
            }

            if (a * a + b * b == c * c || b * b + c * c == a * a || c * c + a * a == b * b) {
                System.out.println("right");
            } else {
                System.out.println("wrong");
            }
        }
    }
}
