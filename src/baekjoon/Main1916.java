package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1916 {

    static int N;
    static int M;
    static LinkedList<Edge>[] edges;
    static int[] d;

    private static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void solve(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        boolean[] visited = new boolean[N + 1];

        for (Edge edge : edges[start]) {
            // 동일 경로에 비용이 다른 버스가 있을 수 있음
            if (edge.cost < d[edge.to]) d[edge.to] = edge.cost;
            pq.add(edge);
        }
        visited[start] = true;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int v = edge.to;

            // 목적지가 이미 방문되었을 경우는 패쓰
            if (visited[v]) continue;

            for (Edge edge_u : edges[v]) {
                int u = edge_u.to;
                if (!visited[u]) {
                    int new_d = d[v] + edge_u.cost;
                    if (new_d < d[u]) {
                        d[u] = new_d;
                        pq.add(new Edge(u, new_d));
                    }
                }
            }
            visited[v] = true;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1916.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new LinkedList[N + 1];
        d = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new LinkedList<Edge>();
        }

        for (int i = 1; i <= N; i++) {
            d[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[n1].add(new Edge(n2, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        solve(start);

        System.out.println(d[end]);
    }
}
