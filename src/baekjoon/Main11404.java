package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 플로이드 와샬 공부해서 풀음
 근데 도저히 이해가 안간다
 어떻게 이게 최소 경로를 보장하는지 모르겠다
 증명을 찾아보려고 해도 증명을 찾을 수가 없다
 */
public class Main11404 {

    final static int INF = 10000001;
    static int N;
    static int M;
    static int[][] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11404.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        d = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (d[from][to] != 0) d[from][to] = Math.min(d[from][to], cost);
            else d[from][to] = cost;
        }

        for (int k = 1; k <= N; k++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    int newD = d[from][k] + d[k][to];
                    if (newD < d[from][to]) d[from][to] = newD;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (d[i][j] == INF) sb.append("0 ");
                else sb.append(d[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
