package 공부.최단경로_ShortestPath;

import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    // 그래프에서 최단 거리를 구하는 알고리즘
    // 출발 노드와 모든 노드간의 최단 거리 탐색
    // 에지는 모두 양수인 것이 특징
    // 시간복잡도는 O(ElogV)

    final static int INF = Integer.MAX_VALUE;
    static int N;
    static int[] d;
    static List<Node>[] list;

    private static class Node implements Comparable<Node> {
        int num;
        int cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void solve(int start) {
        // 1. 최단 거리 배열 초기화
        // 출발 노드는 0
        // 다른 노드는 무한으로 초기화함
        for (int i = 1; i <= N; i++) {
            d[N] = INF;
        }
        d[start] = 0;

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(start, 0));

        // 2. 값이 가장 작은 노드 고르기
        // 최단 거리 배열에서 현재 값이 가장 작은 노드를 고름
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.num;
            if (visited[v]) continue;
            visited[v] = true;

            for (Node u_node : list[v]) {
                if (visited[u_node.num]) continue;
                int u = u_node.num;

                // 3. 최단 거리 배열 업데이트
                // 선택한 노드에 연결된 노드의 값을 업데이트 함
                int newD = d[v] + u_node.cost;
                if (newD < d[u]) {
                    d[u] = newD;
                    pq.add(new Node(u, newD));
                }
            }
        }
        // 시작 노드부터 다른 노드까지 최단 거리 구하기 완료
    }
    // 다익스트라 정당성 증명
    // 다음에 가야할 노드가 A노드라고 한다면
    // 그럼 A노드로 가기위한 cost가 가장 작다는 뜻임
    // 그런데 만약 A노드가 최소가 아니라 A노드를 가기 위해서는 B노드를 거쳐가야 한다면?
    // 그럼 B노드로 가기 위한 cost가 A노드로 가기 위한 cost보다 작아야됨
    // 처음에 가장 작은 cost가 A노드라고 했으므로 모순이 생김
}
