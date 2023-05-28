package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 7분 구현 11분
 다익스트라 + 우선순위큐로 풀음
 처음 감염된 컴퓨터부터 시작해서
 주위로 퍼져나가면서
 현재 컴퓨터까지 오는데 걸리는 시간(d[v.num]) + 앞으로 갈 컴퓨터까지 가는데 걸리는 시간(u.cost)
 과
 앞으로 갈 컴퓨터까지 가는데 걸리는 최소 시간(d[u.num])을 비교해가며
 컴퓨터를 감염시킴

 컴퓨터를 다 감염시키면
 해당 컴퓨터까지 감염되는데 걸리는 최소 시간(d)에서
 감염된 컴퓨터의 개수, 걸리는 시간의 최대 시간을 구해줌
 */
public class Main10282 {

    static final int INF = 20000000;
    static int N;   // 컴퓨터의 개수
    static int M;   // 의존성 개수
    static int start;   // 감염된 컴퓨터
    static int[] d; // 해당 컴퓨터까지 감염되는데 걸리는 최소 시간
    static List<Node>[] list;   // 컴퓨터 의존 정보
    static boolean[] visited;
    static PriorityQueue<Node> pq;
    static int count;
    static int max;

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

    // 다익스트라
    // 시작 노드에서부터 갈 수 있는 노드들의 최소값 구해줌
    static void solve() {
        pq = new PriorityQueue();
        pq.add(new Node(start, 0));
        d[start] = 0;
        visited[start] = true;

        while (!pq.isEmpty()) {
            Node v = pq.poll();
            visited[v.num] = true;

            for (Node u : list[v.num]) {
                if (visited[u.num]) continue;

                int newD = d[v.num] + u.cost;
                if (newD < d[u.num]) {
                    d[u.num] = newD;
                    pq.add(new Node(u.num, newD));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10282.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            start = Integer.parseInt(st.nextToken());
            d = new int[N + 1];
            list = new List[N + 1];
            visited = new boolean[N + 1];

            // 값 초기화
            for (int i = 1; i <= N; i++) {
                d[i] = INF;
                list[i] = new ArrayList();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                list[from].add(new Node(to, cost));
            }

            // 각 컴퓨터들의 감염되는데 걸리는 최소 시간 구함
            solve();

            count = 0;  // 감염된 컴퓨터 개수
            max = 0;    // 감염 시간의 최대값
            for (int i = 1; i <= N; i++) {
                if (d[i] == INF) continue;

                count++;
                max = Math.max(max, d[i]);
            }

            sb.append(count + " " + max + "\n");
        }
        System.out.println(sb);
    }
}
