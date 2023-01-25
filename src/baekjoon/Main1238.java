package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 하........................................
 플로이드로 안 풀리길래
 포기하고 한숨 자고 일어났는데
 다익스트라로 푸는 방법이 값자기 생각남
 근데 다익으로도 안풀림
 플로이드나 다익이나 분명 로직은 같은데..
 알고보니 다익에서 pq에 새로운 Node 넣어줄 때
 cost를 d[u]로 넣어줘야 되는데 0으로 넣어줘서 틀렸던거였음

 다익 2번 써서 푸는 방법으로는 풀렸는데
 플로이드가 여전히 안풀림
 알고보니 플로이드 탐색할 때
 j 범위를 <=N으로 해야되는데
 <N으로 해버렸던 거임
 사소한 실수로 몇시간 동안 삽질함
 하.....................................
 */
public class Main1238 {

    final static int INF = 1000000001;
    static int N;
    static int M;
    static int X;
    static List<Node>[] lists1;
    static List<Node>[] lists2;
    static int[] d1;
    static int[] d2;

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

    static void solve(int start, int[] d, List<Node>[] lists) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.num;

            if (visited[v]) continue;
            visited[v] = true;

            for (Node u_node : lists[v]) {
                int u = u_node.num;
                int cost = u_node.cost;

                if (visited[u]) continue;
                int newD = d[v] + cost;
                if (newD < d[u]) {
                    d[u] = newD;
                    pq.add(new Node(u, d[u]));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1238.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        lists1 = new LinkedList[N + 1];
        lists2 = new LinkedList[N + 1];
        d1 = new int[N + 1];
        d2 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            lists1[i] = new LinkedList();
            lists2[i] = new LinkedList();
        }
        for (int i = 1; i <= N; i++) {
            d1[i] = INF;
            d2[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lists1[to].add(new Node(from, cost));
            lists2[from].add(new Node(to, cost));
        }

        // 가는데 걸리는 시간 구하기
        solve(X, d1, lists1);

        // 오는데 걸리는 시간 구하기
        solve(X, d2, lists2);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X) continue;
            int sum = 0;

            sum += d1[i];
            sum += d2[i];

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
