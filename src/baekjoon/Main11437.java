package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 LCA 공부하고 풀어봄
 노드 클래스를 이용히여 노드의 정보를 관리함
 일단은 일반적인 방법을 사용한 LCA 사용해서 풀어봄
 */
public class Main11437 {

    static int N;
    static int M;
    static Node[] nodes;
    static List<Integer>[] lists;

    private static class Node {
        int num;
        int parent;
        int depth;

        public Node(int num) {
            this.num = num;
        }
    }

    // 트리의 부모, 깊이 구하기
    static void init(int start) {
        Queue<Integer> queue = new LinkedList();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            int nextDepth = nodes[v].depth + 1;

            for (int u : lists[v]) {
                if (visited[u]) continue;

                visited[u] = true;
                queue.add(u);
                nodes[u].parent = v;
                nodes[u].depth = nextDepth;
            }
        }
    }

    // 두 노드의 공통 부모 구하기
    // 항상 a가 더 깊은 노드를 받음
    static int solve(int a, int b) {
        // 깊이 동일하게 맞추기
        while (nodes[a].depth != nodes[b].depth) {
            a = nodes[a].parent;
        }

        // 동시에 올라가기
        while (a != b) {
            a = nodes[a].parent;
            b = nodes[b].parent;
        }

        return a;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11437.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        lists = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            lists[i] = new ArrayList();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            lists[n1].add(n2);
            lists[n2].add(n1);
        }

        init(1);

        StringBuilder sb = new StringBuilder();
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            int result;
            if (nodes[n1].depth >= nodes[n2].depth) {
                result = solve(n1, n2);
            } else {
                result = solve(n2, n1);
            }
            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}
