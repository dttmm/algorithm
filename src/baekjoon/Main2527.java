package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

// 50분
public class Main2527 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2527.txt"));

        Scanner sc = new Scanner(System.in);

        for (int k = 0; k < 4; k++) {

            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int p1 = sc.nextInt();
            int q1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int p2 = sc.nextInt();
            int q2 = sc.nextInt();

            char result = 'd';

            if ((x2 <= p1 && y2 <= q1) && (p2 >= x1 && q2 >= y1)) {
                if (x2 == p1 || y2 == q1 || p2 == x1 || q2 == y1) {
                    if ((x2 == p1 && (y2 == q1 || q2 == y1)) || (p2 == x1 && (y2 == q1 || q2 == y1))) {
                        result = 'c';
                    } else {
                        result = 'b';
                    }

                } else {
                    result = 'a';
                }
            }
            System.out.println(result);
        }

    }
}