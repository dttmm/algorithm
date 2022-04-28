package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1197 {
    static int V;
    static int E;
    static PriorityQueue<Node> pq;
    static int[] rep;
    static int answer;

    private static class Node implements Comparable<Node> {
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) return -1;
            else if (this.cost > o.cost) return 1;
            else return 0;
        }
    }

    public static int getRep(int x) {
        if (x == rep[x]) return x;
        rep[x] = getRep(rep[x]);
        return rep[x];
    }

    public static void unionRep(int x, int y) {
        int xRep = getRep(x);
        int yRep = getRep(y);
        if (xRep <= yRep) rep[yRep] = xRep;
        else rep[xRep] = yRep;
    }

    public static boolean isSameRep(int x, int y) {
        if (getRep(x) == getRep(y)) return true;
        return false;
    }

    public static void solve() {
        int count = 1;
        while (count < V) {
            Node node = pq.poll();
            int node1 = node.node1;
            int node2 = node.node2;
            int cost = node.cost;
            if (!isSameRep(node1, node2)) {
                unionRep(node1, node2);
                count++;
                answer += cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1197.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue();
        rep = new int[V + 1];
        answer = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(node1, node2, cost));
        }

        for (int i = 1; i <= V; i++) {
            rep[i] = i;
        }

        solve();
        System.out.println(answer);
    }
}
