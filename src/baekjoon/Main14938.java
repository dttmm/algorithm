package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 처음에는 bfs를 이용해서 풀었는데
 더 최단 경로가 존재할 수도 있는데
 먼저 방문처리를 해버려서
 최단 경로를 못 찾게 되는 문제가 발생하는 결점을 발견

 그래서 그냥 모든 정점에 대해서 다익스트라를 구해서 풀었음
 다익스트라 다 구하고
 d배열 순차탐색해서 m보다 작은 경우에만
 아이템 개수 더해줌

 근데 다익스트라 구현 까먹어서
 삽질하느라 애먹음
 Node클래스에 필드로 from, to 사용했는데
 너무 헷갈려서 그냥 num 필드만 사용함
 */
public class Main14938 {

    static final int INF = Integer.MAX_VALUE;

    static int N;
    static int m;
    static int R;
    static List<Node>[] nodes;
    static int[] items;

    private static class Node implements Comparable<Node> {
        int num;
        int distance;

        public Node(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    static int solve(int start) {
        int max_item = 0;
        boolean[] visited = new boolean[N + 1];
        int[] d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = INF;
        }
        d[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue();

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.num;

            // 방문한 경우 패쓰
            if (visited[v]) continue;
            if (node.distance > m) continue;
            visited[v] = true;

            for (Node node_u : nodes[v]) {
                int u = node_u.num;
                // 거리가 최소거리보다 더 긴 경우 패쓰
                int new_d = d[v] + node_u.distance;
                if (new_d > d[u]) continue;

                d[u] = new_d;
                pq.add(new Node(u, new_d));
            }
        }

        for (int i = 1; i <= N; i++) {
            if (d[i] > m) continue;
            max_item += items[i];
        }

        return max_item;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14938.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new LinkedList();
        }
        items = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            nodes[from].add(new Node(to, distance));
            nodes[to].add(new Node(from, distance));
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            int result = solve(i);
            max = Math.max(max, result);
        }

        System.out.println(max);
    }
}
