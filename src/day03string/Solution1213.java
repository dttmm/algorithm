package day03string;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1213 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day03_1213.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int case_num = sc.nextInt();
            String target = sc.next();
            String s = sc.next();

            int count = 0;

            for (int i = 0; i <= s.length() - target.length(); i++) {
                if (s.charAt(i) == target.charAt(0)) {
                    for (int j = 1; j < target.length(); j++) {
                        if (s.charAt(i + j) != target.charAt(j)) {
                            break;
                        }
                        if (j == target.length() - 1) {
                            count++;
                        }
                    }
                }
            }
            System.out.println("#" + case_num + " " + count);
        }
    }
}
