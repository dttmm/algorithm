package baekjoon.대회2023.solvedac_Grand_Arena_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class MainI {

    static String s;
    static Stack<Character> stack;

    static boolean solve() {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (stack.isEmpty()) {
                if (c == '(') stack.push(c);
                else if (c == ')') return false;
                else if (c == '*') stack.push(c);
                else if (c == '?') stack.push(c);
            } else if (stack.peek() == '(') {
                if (c == '(') stack.push(c);
                else if (c == ')') stack.pop();
                else if (c == '*') {
                    while (!stack.isEmpty() && stack.peek() == '(') stack.pop();
                    stack.push(c);
                } else if (c == '?') stack.pop();
            } else if (stack.peek() == ')') {
                if (c == '(') return false;
                else if (c == ')') stack.push(c);
                else if (c == '*') return false;
                else if (c == '?') return false;
            } else if (stack.peek() == '*') {
                if (c == '(') stack.push(c);
                else if (c == ')') ;
                else if (c == '*') stack.push(c);
                else if (c == '?') stack.push(c);
            } else if (stack.peek() == '?') {
                if (c == '(') stack.push(c);
                else if (c == ')') stack.pop();
                else if (c == '*') stack.push(c);
                else if (c == '?') stack.push(c);
            }
        }

        int count = 0;
        boolean flag = false;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            if (c == '(' || c == ')') return false;

            if (c == '?') count++;
            else if (c == '*') flag = true;
        }

        if (count % 2 == 1 && !flag) return false;

        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_2/I.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            s = br.readLine();
            if (s == null) s = "";
            stack = new Stack();

            boolean result = solve();
            if (result) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
