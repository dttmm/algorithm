package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15650 {
    static int N;
    static int M;
    static int[] tr;
    static boolean[] visited;

    public static void p(int k) {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(tr[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    if (k == 0) {
                        visited[i] = true;
                        tr[k] = i;
                        p(k + 1);
                        visited[i] = false;
                    } else if (tr[k - 1] < i) {
                        visited[i] = true;
                        tr[k] = i;
                        p(k + 1);
                        visited[i] = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15650.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tr = new int[M];
        visited = new boolean[N + 1];
        p(0);
    }
}
