package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main11403 {
    static int N;
    static int[][] arr;
    static int[][] visitied;

    public static void solve(int i) {
        Stack<Integer> stack = new Stack();
        for (int j = 0; j < N; j++) {
            if (arr[i][j] == 1) {
                stack.push(j);
            }
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            visitied[i][j] = 1;
            for (int k = 0; k < N; k++) {
                if (arr[j][k] == 1 && visitied[i][k] == 0) {
                    stack.push(k);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11403.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visitied = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    arr[i][j] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            solve(i);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(visitied[i][j] + " ");
            }
            System.out.println();
        }
    }
}
