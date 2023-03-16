package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 기본적인 순열 문제
 */
public class Main10974 {

    static int N;
    static boolean[] visited;
    static int[] tr;
    static StringBuilder sb;

    static void solve(int k) {
        // N개를 뽑았을 경우
        if (k == N) {
            for (int i = 0; i < N; i++) {
                sb.append(tr[i] + " ");
            }
            sb.append("\n");
        } else {
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                visited[i] = true;
                tr[k] = i + 1;
                solve(k + 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10974.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N];
        tr = new int[N];
        sb = new StringBuilder();

        solve(0);
        System.out.println(sb);
    }
}
