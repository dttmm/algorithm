package algorithm.day05stack2;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Solution1234 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day05_1234.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 1;
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            String s = sc.next();
            Stack<Integer> st = new Stack<>();

            for (int i = 0; i < N; i++) {
                int num = s.charAt(i) - '0';
                if (!st.isEmpty()) {
                    if (st.peek() != num) {
                        st.push(num);
                    } else {
                        st.pop();
                    }
                } else {
                    st.push(num);
                }
            }

            System.out.print("#" + test_case + " ");
            for (int i : st) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
}