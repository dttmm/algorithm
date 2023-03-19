package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 과거의 기억을 떠올려서 풀어봄
 맨 앞에서 부터 돌면서
 피연산자는 그냥 출력해주고
 연산자는 스택에서 관리를 해주는데
 '+'나 '-'의 경우 자신과 높은 녀석이나 동등한 녀석을 스택에서 만날 때마다 pop해줌
 '*'나 '/'의 경우 자신과 동등한 녀석을 스택에서 만날 때마다 pop해줌
 '('의 경우 그냥 스택에 넣어주고
 ')'의 경우 '('를 만날 때까지 스택에서 만나는 녀석들 다 pop해줌
 */
public class Main1918 {

    static String s;
    static StringBuilder sb;

    // + or - 판별
    static boolean isOperator1(char c) {
        return c == '+' || c == '-';
    }

    // * or / 판별
    static boolean isOperator2(char c) {
        return c == '*' || c == '/';
    }

    static void solve() {
        Stack<Character> stack = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 피연산자
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
            // + or -
            else if (isOperator1(c)) {
                while (!stack.isEmpty() && (isOperator1(stack.peek()) || isOperator2(stack.peek())))
                    sb.append(stack.pop());

                stack.add(c);
            }
            // * or /
            else if (isOperator2(c)) {
                while (!stack.isEmpty() && (isOperator2(stack.peek())))
                    sb.append(stack.pop());

                stack.add(c);
            }
            // (
            else if (c == '(') {
                stack.add(c);
            }
            // )
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    sb.append(stack.pop());

                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1918.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        sb = new StringBuilder();

        solve();

        System.out.println(sb);
    }
}
