package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main4386 {
    static int N;
    static Node[] nodes;
    static PriorityQueue<Cost> pq;
    static int[] rep;
    static double answer;

    private static class Node {
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Cost implements Comparable<Cost> {
        int node1;
        int node2;
        Double cost;

        public Cost(int node1, int node2, Double cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cost o) {
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

    public static void solve() {
        int count = 1;
        while (count < N) {
            Cost cost = pq.poll();
            if (!isSameRep(cost.node1, cost.node2)) {
                unionRep(cost.node1, cost.node2);
                count++;
                answer += cost.cost;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4386.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        pq = new PriorityQueue();
        rep = new int[N];
        answer = 0.0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double cost = Math.sqrt(Math.pow((nodes[i].x - nodes[j].x), 2) + Math.pow((nodes[i].y - nodes[j].y), 2));
                pq.add(new Cost(i, j, cost));
            }
        }

        for (int i = 0; i < N; i++) {
            rep[i] = i;
        }

        solve();
        System.out.printf("%.2f", answer);
    }
}
