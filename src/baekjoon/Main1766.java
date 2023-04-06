package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 위상 정렬 공부하고 풀어봄
 문제 우선순위가 같을 경우
 문제 번호가 낮은 것 부터 풀어야 되기 때문에
 우선순위큐를 이용
 num: 문제 번호
 order: 문제 우선순위
 */
public class Main1766 {

    static int N;
    static int M;
    static int[] in;    // 진입차수 정보 저장
    static List<Integer>[] lists;   // 인접 노드 정보 저장
    static StringBuilder sb;

    private static class Node implements Comparable<Node> {
        int num;    // 노드의 번호
        int order;  // 노드 순서

        public Node(int num, int order) {
            this.num = num;
            this.order = num;
        }

        @Override
        public int compareTo(Node o) {
            if (this.order != o.order) return this.order - o.order;
            return this.num - o.num;
        }
    }

    static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue();
        // 진입 차수 0인 놈들 담음
        for (int i = 1; i <= N; i++) {
            if (in[i] > 0) continue;
            pq.add(new Node(i, 0));
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            sb.append(node.num + " ");

            for (int u : lists[node.num]) {
                in[u]--;
                // 이웃의 진입 차수가 0이 된 경우
                if (in[u] == 0) pq.add(new Node(u, node.order + 1));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1766.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N + 1];
        lists = new List[N + 1];
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            lists[from].add(to);
            in[to]++;
        }

        solve();
        System.out.println(sb);
    }
}
