package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main1874 {

    static int N;
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1874.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        stack = new Stack();

        int current = 1;
        for (int k = 0; k < N; k++) {
            int target = Integer.parseInt(br.readLine());

            // current 보다 target이 더 크면
            if (current <= target) {

                // target이 나올 때 까지 스택에 push
                while (current != target) {
                    stack.push(current);
                    sb.append("+\n");
                    current++;
                }
                sb.append("+\n");

                // 이후에 pop
                sb.append("-\n");
                current++;
            }
            // current 보다 target이 더 작으면
            else {
                // 스택에서 pop후
                int n = stack.pop();
                // pop한 결과가 target과 같은지 확인
                if (n != target) {
                    System.out.println("NO");
                    return;
                }

                sb.append("-\n");
            }
        }
        System.out.println(sb);
    }
}
