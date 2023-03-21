package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 오랫동안 안풀리던 문제 드디어 해결
 벨만포드 다시 공부하고 풀어봄

 와
 벨만포드 돌릴 때,
 시작 지점이 INF인 경우 continue하는 조건을 빼면
 1번 노드에서만 벨만포드를 돌려도
 다른 노드에서 사이클이 발생하는지 확인할 수 있구낭
 단, INF는 MAX_VALUE로 하면 오버플로우 나서 안됨
 */
public class Main1865 {

    static final int INF = 10000000;
    static int N;
    static int M;
    static int W;
    static List<Edge> edges;
    static int[] d;

    private static class Edge {
        int from;
        int to;
        int cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static boolean solve() {
        boolean flag = false; // 순환 플래그

        // 벨만 포드
        for (int i = 1; i <= N; i++) {
            for (Edge edge : edges) {

                // if(d[edge.from] == INF) continue 이 조건 검사 빼야지 나와 연결되어있지 않은 곳에서의 사이클 감지 가능

                int newD = d[edge.from] + edge.cost;
                if (newD < d[edge.to]) {
                    d[edge.to] = newD;

                    if (i != N) continue;
                    flag = true;
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1865.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            edges = new ArrayList();
            d = new int[N + 1];

            // 1번 노드 기준으로 벨만포드 돌릴거임
            for (int i = 2; i <= N; i++) {
                d[i] = INF;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(n1, n2, cost));
                edges.add(new Edge(n2, n1, cost));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, cost * -1));
            }

            boolean result = solve();
            if (result) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
        bw.close();
    }
}
