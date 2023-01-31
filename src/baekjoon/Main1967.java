package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 실패해서 다른 코드 봄
 트리문제여서 무조건 트리 구조로만 생각했는데
 그냥 그래프로 노드 연결해서 생각하는 방법이 있었구나
 부모 자식 관계여도 그냥 서로 연결해주고
 연결된 노드가 하나뿐이면 리프 노드라고 판별할 수 있겠구나

 리프노드에서 다른 리프노드까지의 길이를 완탐으로 모두 구해서
 최대값 찾음
 */
public class Main1967 {

    static int N;
    static List<Node>[] nodeList;
    static int max;
    static boolean[] visited;

    private static class Node {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    // 어떤 리프노드에서 다른 노드들 까지의 거리를 dfs로 구함
    static void solve(int index, int sum) {
        max = Math.max(max, sum);

        for (Node node : nodeList[index]) {
            if (visited[node.to]) continue;
            visited[node.to] = true;
            solve(node.to, sum + node.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1967.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodeList = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            nodeList[i] = new ArrayList();
        }
        max = 0;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodeList[n1].add(new Node(n2, cost));
            nodeList[n2].add(new Node(n1, cost));
        }

        for (int i = 1; i <= N; i++) {
            // 리프 노드인 경우에만 통과
            if (nodeList[i].size() > 1) continue;

            visited = new boolean[N + 1];
            visited[i] = true;

            // 해당 리프노드에서 부터 다른 모든 리프노드 까지의 거리 계산
            solve(i, 0);
        }

        System.out.println(max);
    }
}
