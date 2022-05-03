package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2887 {

    static int N;
    static Node[] nodes;
    static PriorityQueue<Cost> pq;
    static int[] rep;
    static long answer;

    public static class Node {
        int idx;
        long x;
        long y;
        long z;

        public Node(int idx, long x, long y, long z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Cost implements Comparable<Cost> {
        int node1;
        int node2;
        long cost;

        public Cost(int node1, int node2, long cost) {
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

    // 큐에 cost 추가
    public static void addPq() {
        for (int i = 0; i < N - 1; i++) {
            Node node1 = nodes[i];
            Node node2 = nodes[i + 1];
            long cost = Math.min(Math.abs(node1.x - node2.x), Math.abs(node1.y - node2.y));
            cost = Math.min(cost, Math.abs(node1.z - node2.z));
            pq.add(new Cost(node1.idx, node2.idx, cost));
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
        System.setIn(new FileInputStream("res/baekjoon/2887.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        pq = new PriorityQueue();
        rep = new int[N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            nodes[i] = new Node(i, x, y, z);
        }

        // x기준 정렬
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.x < o2.x) return -1;
                else if (o1.x > o2.x) return 1;
                else return 0;
            }
        });
        addPq();

        // y기준 정렬
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.y < o2.y) return -1;
                else if (o1.y > o2.y) return 1;
                else return 0;
            }
        });
        addPq();

        // z기준 정렬
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.z < o2.z) return -1;
                else if (o1.z > o2.z) return 1;
                else return 0;
            }
        });
        addPq();

        for (int i = 0; i < N; i++) {
            rep[i] = i;
        }

        solve();

        System.out.println(answer);
    }
}
