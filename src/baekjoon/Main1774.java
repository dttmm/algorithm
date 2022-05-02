package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1774 {
    static int N;
    static int M;
    static PriorityQueue<Node> pq;
    static long[][] nodes;
    static int[] rep;
    static double answer;

    private static class Node implements Comparable<Node> {
        int node1;
        int node2;
        double cost;

        public Node(int node1, int node2, double cost) {
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
        if (rep[x] == x) return x;
        rep[x] = getRep(rep[x]);
        return rep[x];
    }

    public static void unionRep(int x, int y) {
        int xRep = getRep(x);
        int yRep = getRep(y);
        if (xRep >= yRep) rep[xRep] = yRep;
        else rep[yRep] = xRep;
    }

    public static boolean isSameRep(int x, int y) {
        if (getRep(x) == getRep(y)) return true;
        return false;
    }

    public static void solve(int count) {
        while (count < N - 1) {
            Node node = pq.poll();
            if (!isSameRep(node.node1, node.node2)) {
                unionRep(node.node1, node.node2);
                count++;
                answer += node.cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1774.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue();
        nodes = new long[2][N + 1];
        rep = new int[N + 1];
        answer = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            nodes[0][i] = x;
            nodes[1][i] = y;
        }

        for (int i = 1; i <= N; i++) {
            rep[i] = i;
        }

        int count = 0;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            if (!isSameRep(node1, node2)) {
                unionRep(node1, node2);
                count++;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double cost = Math.sqrt(Math.pow((nodes[0][i] - nodes[0][j]), 2) + Math.pow((nodes[1][i] - nodes[1][j]), 2));
                if (!isSameRep(i, j)) {
                    pq.add(new Node(i, j, cost));
                }
            }
        }

        solve(count);
        System.out.printf("%.2f", answer);
    }
}
