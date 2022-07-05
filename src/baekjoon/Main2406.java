package baekjoon;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2406 {
    static int N;
    static int M;
    static int[] rep;
    static PriorityQueue<Node> pq;
    static int connected;
    static BufferedWriter bw;

    private static class Node implements Comparable<Node> {
        int v1;
        int v2;
        int cost;

        public Node(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
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

    public static int solve() throws IOException {
        int n = connected;
        int cost = 0;
        while (n < N - 2) {
            Node node = pq.poll();
            if (!isSame(node.v1, node.v2)) {
                union(node.v1, node.v2);
                n++;
                cost += node.cost;
                bw.write(node.v1 + " ");
                bw.write(node.v2 + "");
                bw.newLine();
            }
        }
        return cost;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2406.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        rep = new int[N + 1];
        pq = new PriorityQueue();
        connected = 0;
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 1; i <= N; i++) {
            rep[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (!isSame(v1, v2)) {
                union(v1, v2);
                connected++;
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            if (i > 1) {
                for (int j = 1; j <= N; j++) {
                    if (j > i) {
                        int cost = Integer.parseInt(st.nextToken());
                        pq.add(new Node(i, j, cost));
                    } else st.nextToken();
                }
            }
        }

        System.out.print(solve() + " ");
        System.out.println(N - 2 - connected);
        bw.close();

    }
}
