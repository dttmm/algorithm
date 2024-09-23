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

    // B. 빠르게 구하는 방법
    // 기존에는 깊이를 맞추거나 같은 부모를 찾을 때
    // 한 칸씩 위로 올라갔지만
    // 2^k씩 위로 올라가면 빠르게 찾을 수 있음
    // 그러기 위해서는 많은 작업을 해줘야됨

    // 내 바로 위의 부모 뿐만 아니라
    // 2^k 위의 부모도 저장해야됨
    // p[K][N]: N번 노드의 2^k번째 부모
    // k는 트리의 깊이 > 2^k를 만족하는 최대값

    // 다음을 만족
    // p[K][N] = p[K - 1][p[K - 1][N]]
    // ex) N의 2^3번째 부모는 N의 2^2번째 부모의 2^2번째 부모임

    // 깊이 맞추기
    // 2^k 단위로 깊이를 점프하면서 맞추면 됨

    // 최소 공통 조상 찾기
    // 2^k 단위로 깊이를 점프해야됨
    // k를 1씩 감소시키면서 두 노드의 부모가 최초로 달라지는 값을 찾으면
    // 거기로 점프하고 k를 1 감소시키고 비교 반복

    // k가 0이 될 때까지 비교했는데
    // 부모가 똑같으면 그것이 바로 최소 공통 조상
    // 부모가 같지 않다면 바로 위 노드가 최소 공통 조상
}
