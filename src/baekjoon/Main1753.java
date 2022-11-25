package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1753 {

    static int V;
    static int E;
    static LinkedList<Node>[] list;
    static int[] d;
    static int INF = Integer.MAX_VALUE;

    private static class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void solve(int start) {
        PriorityQueue<Node> pq = new PriorityQueue();
        d[start] = 0;
        boolean[] visited = new boolean[V + 1];
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.num;
            if (visited[v]) continue;

            visited[v] = true;
            for (Node u_node : list[v]) {
                if (visited[u_node.num]) continue;

                int new_d = d[v] + u_node.cost;
                if (new_d < d[u_node.num]) {
                    d[u_node.num] = new_d;
                    pq.add(new Node(u_node.num, new_d));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1753.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        list = new LinkedList[V + 1];
        d = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            list[i] = new LinkedList();
            d[i] = INF;
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, cost));
        }

        solve(start);

        for (int i = 1; i <= V; i++) {
            int cost = d[i];
            if (cost == INF) {
                bw.write("INF\n");
            } else {
                bw.write(cost + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
