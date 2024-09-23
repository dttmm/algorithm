package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * SCC로 풀어보려고 했는데
 * 안되넹
 */

public class Main9466_2 {

    static int N;
    static int[] select;
    static Stack<Integer> stack;
    static int[] p;
    static boolean[] finished;
    static int answer;
    static int id;

    static int solve(int v) {
        stack.add(v);
        p[v] = id++;

        int parent = p[v];
        int u = select[v];

        if (p[u] == 0) parent = Math.min(parent, solve(u));
        else if (!finished[u]) parent = Math.min(parent, p[u]);

        if (parent == p[v] && select[v] != v) {
            while (true) {
                int n = stack.pop();
                finished[n] = true;
                answer--;
                if (n == v) break;
            }
        } else if (select[v] == v) {
            int n = stack.pop();
            finished[n] = true;
            answer--;
        }

        return parent;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9466.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            select = new int[N + 1];
            stack = new Stack();
            p = new int[N + 1];
            finished = new boolean[N + 1];
            answer = N;
            id = 1;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int n = Integer.parseInt(st.nextToken());
                select[i] = n;
            }

            for (int i = 1; i <= N; i++) {
                if (p[i] != 0) continue;
                solve(i);
            }

            System.out.println(answer);
        }
    }
}
