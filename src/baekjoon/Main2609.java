package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main2609 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2609.txt"));

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();

        int min = a > b ? b : a;

        int ans1 = 0;
        for (int i = 1; i <= min; i++) {
            if (a % i == 0 && b % i == 0) {
                ans1 = i;
            }
        }

        int ans2 = ans1 * (a / ans1) * (b / ans1);

        System.out.println(ans1);
        System.out.println(ans2);
    }
}
