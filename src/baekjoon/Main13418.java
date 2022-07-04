package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main13418 {
    static int N;
    static int M;
    static PriorityQueue<Node> pq_worst;
    static PriorityQueue<Node> pq_best;
    static int[] rep;

    private static class Node {
        int v1;
        int v2;
        int cost;

        public Node(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
    }

    public static int getRep(int x) {
        if (rep[x] == x) return x;
        else return rep[x] = getRep(rep[x]);
    }

    public static void join(int x, int y) {
        int xRep = getRep(x);
        int yRep = getRep(y);
        if (xRep < yRep) rep[yRep] = xRep;
        else rep[xRep] = yRep;
    }

    public static boolean isSame(int x, int y) {
        if (getRep(x) == getRep(y)) return true;
        else return false;
    }

    public static int solve(PriorityQueue<Node> pq) {
        for (int i = 0; i <= N; i++) {
            rep[i] = i;
        }
        int n = 0;
        int up = 0;
        while (n < N) {
            Node node = pq.poll();
            if (!isSame(node.v1, node.v2)) {

                join(node.v1, node.v2);
                if (node.cost == 0) up++;
                n++;
            }
        }
        return (int) Math.pow(up, 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13418.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq_worst = new PriorityQueue(new Comparator<Node>() { // 오르막길 우선
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        pq_best = new PriorityQueue(new Comparator<Node>() { // 내리막길 우선
            @Override
            public int compare(Node o1, Node o2) {
                return o2.cost - o1.cost;
            }
        });

        rep = new int[N + 1];

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Node node = new Node(v1, v2, cost);
            pq_worst.add(node);
            pq_best.add(node);
        }

        System.out.println(solve(pq_worst) - solve(pq_best));
    }
}
