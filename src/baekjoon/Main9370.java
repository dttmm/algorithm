package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 15분 구현 21분 디버깅 54분
 시작지점에서 다익스트라로 모든 노드에 대해 최단 거리를 구할 때,
 특정 엣지(G-H)를 지나온 것을 어떻게 판별할 수 있을까

 처음에는
 이전에 지나온 노드 정보를 저장하는 prev배열을 통해
 최단 거리 경로를 저장했지만
 최단 거리가 여러개인 경우
 특정 엣지(G-H)를 지난 경로와 지나지 않은 경로가 둘 다 최단거리일 경우 예외가 발생

 결국 특정 엣지(G-H)를 지났을 때와 지나지 않았을 떄의 경로를 bfs로 돌려서
 모든 경우의 수를 고려하여 특정 엣지(G-H)를 지나면서 목적지 후보인 것을 찾으려고 했지만
 경우의 수가 너무 많아서 메모리 초과..

 방법을 찾지 못하고 헬프!!
 와...
 이런 아이디어는 대체 어떻게 떠올리는 것이냐

 +
 다른 풀이보고 제출했는데도 계속 틀리길래 뭐지 했는데
 INF를 Integer.MAX_VALUE로 설정한 것이 패착이었다
 Integer.MAX_VALUE는 홀수라서
 목적지 후보가 Integer.MAX_VALUE일 경우 정답으로 판별되는 예외가 발생쓰..
 */
public class Main9370 {

    final static int INF = 100000000;
    static int N;
    static int M;
    static int T;
    static int S;
    static int G;
    static int H;
    static List<Node>[] lists;
    static List<Integer> answerList;
    static int[] d;

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
        for (int i = 1; i <= N; i++) {
            d[i] = INF;
        }
        d[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue();
        boolean[] visited = new boolean[N + 1];
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node v_node = pq.poll();

            if (visited[v_node.num]) continue;
            visited[v_node.num] = true;

            for (Node u_node : lists[v_node.num]) {
                if (visited[u_node.num]) continue;

                int newD = d[v_node.num] + u_node.cost;

                if (newD < d[u_node.num]) {
                    d[u_node.num] = newD;
                    pq.add(new Node(u_node.num, newD));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9370.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            G = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            lists = new List[N + 1];
            answerList = new ArrayList();
            d = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                lists[i] = new ArrayList();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if ((a == G && b == H) || (a == H && b == G)) {
                    lists[a].add(new Node(b, 2 * d - 1));
                    lists[b].add(new Node(a, 2 * d - 1));
                } else {
                    lists[a].add(new Node(b, 2 * d));
                    lists[b].add(new Node(a, 2 * d));
                }
            }

            solve(S);

            for (int i = 0; i < T; i++) {
                int n = Integer.parseInt(br.readLine());
                if (d[n] % 2 == 1) {
                    answerList.add(n);
                }
            }

            Collections.sort(answerList);
            for (int n : answerList) {
                sb.append(n + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
