package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main10773 {
    static int K;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10773.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        int[] stack = new int[K];
        int top = -1;
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                top--;
            } else {
                stack[++top] = n;
            }
        }
        int sum = 0;
        for (int i = 0; i <= top; i++) {
            sum += stack[i];
        }
        System.out.println(sum);
    }
}
