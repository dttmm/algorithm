package algorithm.day04stack1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1218 {


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day04_1218.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            String s = sc.next();
            char[] cArr = new char[N];

            int cur = 0;
            int flag = 1;

            for (int i = 0; i < N; i++) {
                char c = s.charAt(i);
                if (c == '{' || c == '[' || c == '<' || c == '(') {
                    cArr[cur++] = c;
                } else if (c == '}' && cArr[cur - 1] == '{') {
                    cur--;
                } else if (c == ']' && cArr[cur - 1] == '[') {
                    cur--;
                } else if (c == '>' && cArr[cur - 1] == '<') {
                    cur--;
                } else if (c == ')' && cArr[cur - 1] == '(') {
                    cur--;
                } else {
                    flag = 0;
                    break;
                }
            }
            System.out.println("#" + test_case + " " + flag);
        }
    }
}