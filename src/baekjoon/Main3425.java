package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main3425 {

    static ArrayList<String> list;
    static Stack<Long> stack;
    static boolean flag;

    public static void run() {
        for (int i = 0; i < list.size(); i++) {
            String command = list.get(i);

            switch (command) {
                case "POP":
                    POP();
                    break;
                case "INV":
                    INV();
                    break;
                case "DUP":
                    DUP();
                    break;
                case "SWP":
                    SWP();
                    break;
                case "ADD":
                    ADD();
                    break;
                case "SUB":
                    SUB();
                    break;
                case "MUL":
                    MUL();
                    break;
                case "DIV":
                    DIV();
                    break;
                case "MOD":
                    MOD();
                    break;
                default: {
                    int n = Integer.parseInt(command.substring(4));
                    NUM(n);
                }
            }
            if (flag) break;
        }
    }

    public static boolean isError() {
        if (stack.isEmpty()) {
            flag = true;
            return true;
        }
        return false;
    }

    public static boolean isError2() {
        if (isError()) {
            return true;
        }

        long n = stack.pop();

        if (isError()) {
            return true;
        }

        stack.push(n);
        return false;
    }

    // X를 스택의 가장 위에 저장한다. (0 ≤ X ≤ 109)
    public static void NUM(long x) {
        stack.push(x);
    }

    // 스택 가장 위의 숫자를 제거한다.
    public static void POP() {
        if (isError()) {
            return;
        }

        stack.pop();
    }

    // 첫 번째 수의 부호를 바꾼다. (42 -> -42)
    public static void INV() {
        if (isError()) {
            return;
        }

        long num = stack.pop();
        num = -1 * num;
        stack.push(num);
    }

    // 첫 번째 숫자를 하나 더 스택의 가장 위에 저장한다.
    public static void DUP() {
        if (isError()) {
            return;
        }

        long num = stack.peek();
        stack.push(num);
    }

    // 첫 번째 숫자와 두 번째 숫자의 위치를 서로 바꾼다.
    public static void SWP() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        stack.push(n1);
        stack.push(n2);
    }

    // 첫 번째 숫자와 두 번째 숫자를 더한다.
    public static void ADD() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        long result = n1 + n2;
        if (result > Math.pow(10, 9) || -1 * result > Math.pow(10, 9)) {
            flag = true;
            return;
        }
        stack.push(result);
    }

    // 첫 번째 숫자와 두 번째 숫자를 뺀다. (두 번째 - 첫 번째)
    public static void SUB() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        long result = n2 - n1;
        if (result > Math.pow(10, 9) || -1 * result > Math.pow(10, 9)) {
            flag = true;
            return;
        }
        stack.push(result);
    }

    // 첫 번째 숫자와 두 번째 숫자를 곱한다.
    public static void MUL() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        long result = n2 * n1;
        if (result > Math.pow(10, 9) || -1 * result > Math.pow(10, 9)) {
            flag = true;
            return;
        }
        stack.push(result);
    }

    // 첫 번째 숫자로 두 번째 숫자를 나눈 몫을 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    public static void DIV() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        if (n1 == 0) {
            flag = true;
            return;
        }
        stack.push(n2 / n1);
    }

    // 첫 번째 숫자로 두 번째 숫자를 나눈 나머지를 저장한다. 두 번째 숫자가 피제수, 첫 번째 숫자가 제수이다.
    public static void MOD() {
        if (isError2()) {
            return;
        }

        long n1 = stack.pop();
        long n2 = stack.pop();

        if (n1 == 0) {
            flag = true;
            return;
        }
        stack.push(n2 % n1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3425.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String command = br.readLine();
        while (!command.equals("QUIT")) {

            list = new ArrayList<>();

            while (!command.equals("END")) {
                list.add(command);
                command = br.readLine();
            }

            long N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                stack = new Stack<>();
                long n = Integer.parseInt(br.readLine());
                stack.push(n);

                flag = false;
                run();

                if (flag) {
                    System.out.println("ERROR");
                    continue;
                }

                if (stack.isEmpty()) {
                    System.out.println("ERROR");
                } else {
                    long result = stack.pop();
                    if (stack.isEmpty()) {
                        System.out.println(result);
                    } else {
                        System.out.println("ERROR");
                    }
                }
            }
            System.out.println(br.readLine());

            command = br.readLine();
        }
    }
}
