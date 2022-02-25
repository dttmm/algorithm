package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main17413 {
    static StringBuilder sb;
    static Stack<Character> stack;

    public static void print() {
        while (!stack.isEmpty()){
            char c = stack.pop();
            sb.append(c);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17413.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        stack = new Stack<>();
        String s = br.readLine();

        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '<') {
                if (flag) print();
                sb.append(c);
                flag = false;
            } else if (c == ' ' || c == '>') {
                if (flag) print();
                sb.append(c);
                if (c == ' ' && !flag) {

                } else {
                    flag = true;
                }
            } else {
                if (!flag) {
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
        }
        if (flag) print();
        System.out.println(sb.toString());
    }
}
