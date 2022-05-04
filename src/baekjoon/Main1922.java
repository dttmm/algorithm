package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1922 {
    static int N;
    static int M;
    static PriorityQueue<Cost> pq;
    static int answer;
    static int[] rep;

    private static class Cost implements Comparable<Cost> {
        int node1;
        int node2;
        int cost;

        public Cost(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cost o) {
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
            Cost cost = pq.poll();
            if (!isSameRep(cost.node1, cost.node2)) {
                unionRep(cost.node1, cost.node2);
                count++;
                answer += cost.cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1922.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();
        answer = 0;
        rep = new int[N + 1];

        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Cost(node1, node2, cost));
        }

        for (int i = 1; i <= N; i++) {
            rep[i] = i;
        }

        solve();
        System.out.println(answer);
    }
}
