package algorithm.day04stack1;

public class Practice2 {
    static char[] arr = new char[100];
    static int top = -1;

    public static void push(char c) {
        top += 1;
        if (top >= 100) {
            System.out.println("overflow");
            top--;
        } else {
            arr[top] = c;
        }
    }

    public static char pop() {
        if (top >= 0) {
            return arr[top--];
        } else {
            return 'e';
        }
    }

    public static void main(String[] args) {
//        String s = "()()((()))";
//        String s = "((()((((()()((()())((()))))))))";
        String s = "()()()((()))(()())";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                push(c);
            } else {
                char cPop = pop();
                if (cPop != '(') {
                    System.out.println("e: 괄호오류");
                    break;
                }
            }
        }
        if (top != -1) {
            System.out.println("e: 남아있음");
        } else {
            System.out.println("이상없음");
        }
    }
}
