package algorithm.day05stack2;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Stack;

public class Solution1224 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day05_1224.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            char[] arr = sc.next().toCharArray();
            Stack<Character> st = new Stack<>();
            Stack<Integer> st_cal = new Stack<>();
            char[] formula = new char[N];
            int top = -1;
            int start = -1;

            for (int i = 0; i < N; i++) {
                char c = arr[i];
                if (c == '(') {
                    st.push(c);
                } else if (c == '*' || c == '/') {
                    while (!st.isEmpty() && (st.peek() == '*' || st.peek() == '/')) {
                        formula[++top] = st.pop();
                    }
                    st.push(c);
                } else if (c == '+' || c == '-') {
                    while (!st.isEmpty() && (st.peek() == '*' || st.peek() == '/' || st.peek() == '+' || st.peek() == '-')) {
                        formula[++top] = st.pop();
                    }
                    st.push(c);
                } else if (c == ')') {
                    while (st.peek() != '(') {
                        formula[++top] = st.pop();
                    }
                    st.pop();
                } else {
                    formula[++top] = c;
                }
            }
            while (!st.isEmpty()) {
                formula[++top] = st.pop();
            }

            while (start != top) {
                char c = formula[++start];
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    int a = st_cal.pop();
                    int b = st_cal.pop();
                    if (c == '+') {
                        st_cal.push(b + a);
                    } else if (c == '-') {
                        st_cal.push(b - a);
                    } else if (c == '*') {
                        st_cal.push(b * a);
                    } else {
                        st_cal.push(b / a);
                    }
                } else {
                    st_cal.push(c - '0');
                }
            }

            System.out.println("#" + test_case + " " + st_cal.pop());
        }
    }
}