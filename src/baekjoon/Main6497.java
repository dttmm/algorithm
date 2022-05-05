package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main6497 {
    static int N;
    static int M;
    static PriorityQueue<Node> pq;
    static int answer;
    static int[] rep;

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
        rep[x] = getRep(rep[x]);
        return rep[x];
    }

    public static void unionRep(int x, int y) {
        int xRep = getRep(x);
        int yRep = getRep(y);
        if (xRep < yRep) rep[yRep] = xRep;
        else rep[xRep] = yRep;
    }

    public static boolean isSameRep(int x, int y) {
        if (getRep(x) == getRep(y)) return true;
        return false;
    }

    public static void solve() {
        int count = 0;
        while (count < N - 1) {
            Node node = pq.poll();
            if (!isSameRep(node.n1, node.n2)) {
                unionRep(node.n1, node.n2);
                count++;
                answer += node.cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6497.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) break;
            pq = new PriorityQueue();
            answer = 0;
            rep = new int[N];
            int total = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                pq.add(new Node(n1, n2, cost));
                total += cost;
            }

            for (int i = 0; i < N; i++) {
                rep[i] = i;
            }

            solve();
            System.out.println(total - answer);
        }
    }
}
