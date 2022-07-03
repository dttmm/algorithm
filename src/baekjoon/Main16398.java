package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main16398 {
    static int N;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static int[][] arr;

    private static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;

        public Node(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int getParent(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = getParent(parent[x]);
    }

    public static void join(int x, int y) {
        int xParent = getParent(x);
        int yParent = getParent(y);
        if (xParent < yParent) parent[xParent] = yParent;
        else parent[yParent] = xParent;
    }

    public static boolean isSame(int x, int y) {
        if (getParent(x) == getParent(y)) return true;
        else return false;
    }

    public static long solve() {
        int n = 0;
        long total = 0;
        while (n < N - 1) {
            Node node = pq.poll();
            if (!isSame(node.from, node.to)) {
                join(node.from, node.to);
                n++;
                total += node.cost;
            }
        }
        return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16398.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        pq = new PriorityQueue();
        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int cost = arr[i][j];
                pq.add(new Node(i, j, cost));
            }
        }

        System.out.println(solve());
    }
}
