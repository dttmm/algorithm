package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1504 {

    static int N;
    static int E;
    static int[] d;                     // 최단 거리 저장
    static boolean[] visited;           // 방문 표시
    static LinkedList<Edge>[] nodes;    // 연결된 그래트 저장
    static boolean flag;                // 갈수 있는지 없는지 체크

    private static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost != o.cost) return this.cost - o.cost;
            return this.from - o.from;
        }
    }

    // start -> dest 까지의 최단거리 (다익스트라)
    static int solve(int start, int dest) {
        visited = new boolean[N + 1];
        d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = Integer.MAX_VALUE;
        }
        d[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.add(new Edge(start, -1, -1));        // 시작 지점의 번호만 필요함

        while (!pq.isEmpty()) {
            Edge e = pq.poll();

            int v = e.from;

            if (!visited[v]) {
                visited[v] = true;

                for (Edge edge : nodes[v]) {
                    if (!visited[edge.to]) {

                        if (edge.cost + d[v] < d[edge.to]) d[edge.to] = edge.cost + d[v];

                        // start에서 해당 지점(edge.to)까지 가는데 거리가 d[edge.to]이다
                        pq.add(new Edge(edge.to, -1, d[edge.to]));
                    }
                }
            }
        }

        // start 에서 dest까지 갈 수 없는 경우
        if (d[dest] == Integer.MAX_VALUE) flag = true;
        return d[dest];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1504.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodes = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new LinkedList();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[from].add(new Edge(from, to, cost));
            nodes[to].add(new Edge(to, from, cost));
        }

        st = new StringTokenizer(br.readLine());
        int dest1 = Integer.parseInt(st.nextToken());
        int dest2 = Integer.parseInt(st.nextToken());

        flag = false;
        int re1 = solve(1, dest1) + solve(dest1, dest2) + solve(dest2, N);
        if (flag) re1 = Integer.MAX_VALUE;  // 길이 없는 경우

        flag = false;
        int re2 = solve(1, dest2) + solve(dest2, dest1) + solve(dest1, N);
        if (flag) re2 = Integer.MAX_VALUE;  // 길이 없는 경우

        int min = Math.min(re1, re2);

        if (min == Integer.MAX_VALUE) System.out.println("-1"); // 길이 없는 경우
        else System.out.println(min);
    }
}
