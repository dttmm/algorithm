package baekjoon;

import java.io.*;
import java.util.*;

/**
 기존 다익스트라에
 경로 저장하는 배열 하나 만들어서 풀었음
 근데 INF값을 Integer.MAX_VALUE로 안하고
 대강 큰 숫자로 하니까 메모리초과 나네
 맘 편하게 MAX_VALUE로 해야겠다
 pa에 새로운 노드 추가할 때도
 (newD < d[u]) 조건을 만족하는 경우에만 추가해줘야 되는구나
 이 두개 때문에 헤멤

 path배열에서 목적지 인덱스부터 시작해서
 그 전에 어디서 왔는지 확인하면 끝
 */
public class Main11779 {

    final static int INF = Integer.MAX_VALUE;
    static int N;
    static int M;
    static List<Node>[] lists;
    static PriorityQueue<Node> pq;
    static int[] d;
    static int[] path;
    static boolean[] visited;

    private static class Node implements Comparable<Node> {
        int num;
        int to;
        int cost;

        public Node(int num, int to, int cost) {
            this.num = num;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void solve(int start, int end) {
        pq.add(new Node(start, 0, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.num;

            if (visited[v]) continue;
            visited[v] = true;

            for (Node u_node : lists[v]) {
                int u = u_node.to;
                int cost = u_node.cost;

                if (visited[u]) continue;

                int newD = d[v] + cost;
                if (newD < d[u]) {
                    d[u] = newD;
                    path[u] = v;    // u에 가기위해서는 v를 거쳐가야 한다
                    pq.add(new Node(u, 0, newD));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11779.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        lists = new List[N + 1];
        pq = new PriorityQueue();
        d = new int[N + 1];
        path = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            lists[i] = new LinkedList();
        }

        for (int i = 1; i <= N; i++) {
            d[i] = INF;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            Node newNode = new Node(from, to, cost);

            lists[from].add(newNode);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solve(start, end);

        int num = end;
        int count = 1;
        List<Integer> pathList = new ArrayList();
        pathList.add(end);
        while (num != start) {
            pathList.add(path[num]);
            num = path[num];
            count++;
        }

        bw.append(d[end] + "\n");
        bw.append(count + "\n");
        for (int i = pathList.size() - 1; i >= 0; i--) {
            bw.append(pathList.get(i) + " ");
        }

        bw.flush();
        bw.close();
    }
}
