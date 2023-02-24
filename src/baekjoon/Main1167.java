package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 저번에 비슷한 유형의 문제를 풀어봤기 때문에
 트리의 지름을 어떻게 구하는 지는 알고 있었음
 루트에서 제일 먼 노드를 찾고
 해당 노드에서 가장 먼 노드를 찾으면 그게 지름임

 근데, 문제에서는 루트 노드가 명시되어 있지 않아서
 다른 방법으로 풀 수 있는 방법이 없나 머리를 굴려봄
 완탐을 하면 100,000^2으로 시간초과이기 때문에
 무조건 nlogn이나 n으로 풀 수 있는 방법이 없나 생각함
 결국 생각 실패

 그냥 임의의 노드 1에서 부터 가장 먼 노드 찾고
 해당 노드에서 가장 먼 노드 찾았는데
 되네?!
 이게 가능한 이유에 대해 찾아보려고 했지만
 증명이 이해가 되질 않는다..
 */
public class Main1167 {

    static int N;
    static List<Node>[] nodes;

    private static class Node {
        int num;
        int d;

        public Node(int num, int d) {
            this.num = num;
            this.d = d;
        }
    }

    // start 노드에서 가장 먼 노드 반환
    public static Node solve(int start) {
        Node maxNode = new Node(start, 0);  // 가장 먼 노드 담을 변수
        Queue<Node> queue = new LinkedList();
        boolean[] visited = new boolean[N + 1];

        queue.add(new Node(start, 0));
        visited[start] = true;

        // bfs 돌려줌
        while (!queue.isEmpty()) {
            Node v_node = queue.poll();

            for (Node u_node : nodes[v_node.num]) {
                if (visited[u_node.num]) continue;

                int newD = v_node.d + u_node.d;
                queue.add(new Node(u_node.num, newD));
                visited[u_node.num] = true;

                if (newD > maxNode.d) {
                    maxNode.num = u_node.num;
                    maxNode.d = newD;
                }
            }
        }

        return maxNode;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1167.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (to != -1) {
                int d = Integer.parseInt(st.nextToken());
                nodes[from].add(new Node(to, d));

                to = Integer.parseInt(st.nextToken());
            }
        }

        // 임의의 정점에서 가장 먼 노드 구한 뒤
        Node maxNode = solve(1);

        // 해당 노드에서 가장 먼 노드의 길이 구함함
        maxNode = solve(maxNode.num);

        System.out.println(maxNode.d);
    }
}
