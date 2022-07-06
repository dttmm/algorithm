package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main10423 {
    static int N;
    static int M;
    static int K;
    static int[] rep;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int n1;
        int n2;
        int cost;

        public Node(int n1, int n2, int cost) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int getRep(int x) {
        if (rep[x] == x) return x;
        else return rep[x] = getRep(rep[x]);
    }

    public static void union(int x, int y) {
        int xRep = getRep(x);
        int yRep = getRep(y);
        if (xRep < yRep) rep[yRep] = xRep;
        else rep[xRep] = yRep;
    }

    public static boolean isSame(int x, int y) {
        if (getRep(x) == getRep(y)) return true;
        else return false;
    }

    public static int solve() {
        int n = 0;
        int total = 0;
        while (n < N - K) {
            Node node = pq.poll();
            if (!isSame(node.n1, node.n2)) {
                union(node.n1, node.n2);
                total += node.cost;
                n++;
            }
        }
        return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10423.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        rep = new int[N + 1];
        pq = new PriorityQueue();

        for (int i = 1; i <= N; i++) {
            rep[i] = i;
        }

    st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            rep[n] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(n1, n2, cost));
        }

        System.out.println(solve());
    }
}
