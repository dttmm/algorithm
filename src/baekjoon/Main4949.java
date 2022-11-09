package baekjoon;

import java.io.*;
import java.util.Stack;

public class Main4949 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4949.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = br.readLine();
            Stack<Character> stack = new Stack();
            boolean flag = false;   // 중간에 실패 플래그

            if (s.equals(".")) break;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '.') break;

                if (c == '[' || c == '(') stack.push(c);
                else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        bw.write("no\n");
                        flag = true;
                        break;
                    }
                    stack.pop();
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        bw.write("no\n");
                        flag = true;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!flag) {
                if (stack.isEmpty()) bw.write("yes\n");
                else bw.write("no\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
