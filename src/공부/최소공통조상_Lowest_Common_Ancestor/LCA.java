package 공부.최소공통조상_Lowest_Common_Ancestor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LCA {
    static int N;                   // 노드의 개수
    static Node[] nodes;            // 노드의 정보 저장
    static List<Integer>[] lists;   // 인접 노드 정보 저장

    private static class Node {
        int num;    // 노드의 번호
        int parent; // 부모 노드의 번호
        int depth;  // 노드의 깊이

        public Node(int num) {
            this.num = num;
        }
    }

    // 최소공통조상 (LCA)
    // 트리 그래프에서 임의의 두 노드를 선택했을 때
    // 처음 공통으로 만나게 되는 부모 노드
    // 시간복잡도는 트리의 높이에 비례함

    // A. 일반적으로 구하는 방법
    // 트리의 높이가 크지 않을 때 사용
    // 트리의 높이가 커지면 시간복잡도가 커짐

    // A-1. 루트 노드부터 탐색을 시작해서 부모노드와 깊이를 저장 (DFS, BFS 사용)
    // 트리의 부모, 깊이 구하기
    // 루트 노드로 부터 내려가면서
    // 각 노드의 부모, 깊이 정보 구해서 저장해줌
    static void init(int start) {
        // 루트 노드 정보 초기화
        Queue<Integer> queue = new LinkedList();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            int nextDepth = nodes[v].depth + 1; // 현재 노드의 다음 깊이

            // 현재 노드에 연결되어 있는 노드 탐색하면서
            for (int u : lists[v]) {
                if (visited[u]) continue;

                // 부모, 싶이 정보 업데이트
                visited[u] = true;
                queue.add(u);
                nodes[u].parent = v;
                nodes[u].depth = nextDepth;
            }
        }
    }

    // A-2. 두 노드의 공통 부모 구하기
    // 항상 a가 더 깊은 높이 노드를 받음 <- 계산 편하기 하기 위해서
    static int solve(int a, int b) {
        // 선택된 두 노드의 깊이가 다른 경우 깊이 동일하게 맞추기
        // 더 깊은 노드의 노드를 부모 노드로 1개씩 올려 주면서 같은 깊이로 맞춤
        // 이때, 두 노드가 같으면 바로 최소 공통 조상 찾은 거임
        while (nodes[a].depth != nodes[b].depth) {
            a = nodes[a].parent;
        }

        // 깊이가 같은 상태에서는 동시에 부모 노드로 올라가면서
        // 두 노드가 같을 때까지 반복
        while (a != b) {
            a = nodes[a].parent;
            b = nodes[b].parent;
        }

        return a;
    }
}
