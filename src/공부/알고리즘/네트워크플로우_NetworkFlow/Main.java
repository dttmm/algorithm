package 공부.알고리즘.네트워크플로우_NetworkFlow;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    // 네트워크 플로우는 최대 유량을 계산하기 위한 것으로
    // BFS를 이용하는 에드몬드 카프(Edmonds-Karp), DFS를 이용하는 포드풀커슨(Ford-Fulkerson)이 있으며
    // 시간복잡도는 각각 O(V * E^2), O((V+E) * F) F:최대 유량
    // 두 알고리즘보다 더 빠른 디닉(Dinic)을 구현해 볼거임

    // 디닉의 시간복잡도는 O(V^2 * E)
    // BFS로 레벨을 구하기 위해 간선들을 탐색하게 됨 O(E)
    // DFS로 차단 유량을 흘려주면서 sink까지 가는데 최대 V개의 노드를 만남 O(V)
    // BFS + DFS과정을 남은 유량이 없을 때 까지(모든 노드가 관여할 때까지) 반복해 주므로
    // V * (V + E) - > O(V^2 * E)

    static final int start = 0;     // 시작 노드(source)
    static final int end = 100;     // 끝 노드(sink)
    static final int size = 101;    // 노드 전체 개수 (sink와 다를 수 있음)
    static int[] level;             // 각 노드의 레벨
    static int[][] capacity;        // v -> u 노드로 가는 간선의 최대 용량
    static int[][] flow;            // v -> u 노드로 가는 유량
    static List<Integer>[] lists;   // 노드 연결 정보

    // 디닉의 과정

    // 1. BFS를 이용하여 각 노드의 레벨 계산
    // 다른 알고리즘은 다음으로 갈 수 있는 노드의 선택지가 많아서
    // 노드를 잘못 선택했을 경우, 시간이 오래 걸릴 수 있다는 단점이 있음
    // 디닉은 현재 노드의 레벨에서 1 높은 노드만 갈 수 있게하여
    // 갈 수 있는 노드의 선택지를 줄여줌
    // 이를 차단 유량(blocking flow)이라고 함
    static boolean setLevel() {
        // 각 노드의 레벨 초기화
        for (int i = 0; i < size; i++) {
            level[i] = -1;
        }

        Queue<Integer> queue = new LinkedList();
        // 시작 노드 설정
        queue.add(start);
        level[start] = 0;

        while (!queue.isEmpty()) {
            int v = queue.poll();

            // 현재 노드(v)에서 갈 수 있는 이웃 노드(u) 탐색
            for (int u : lists[v]) {
                // 이미 해당 노드 방문했으면 패쓰
                if (level[u] != -1) continue;

                // 현재 노드(v)에서 다음 노드(u)로 가는데
                // 이미 최대 용량만큼 유량이 흐르고 있으면 패쓰
                if (capacity[v][u] - flow[v][u] <= 0) continue;

                // 해당 간선으로 유량이 흐를 수 있으면 레벨 설정
                queue.add(u);
                level[u] = level[v] + 1;
            }
        }

        // 도착점까지 유량이 흐를 수 있는 길이 있는지 없는지 판단
        return level[end] != -1;
    }

    // 2. DFS를 이용하여 현재 노드(v)에서부터 흐를 수 있는 유량(amount) 구하기
    static int getFlow(int v, int amount) {
        // 도착점에 도달한 경우 -> 지금까의 경로 중에 흐를 수 있는 최소 유량 리턴
        if (v == end) return amount;

        // 현재 노드(v)에서 갈 수 있는 이웃 노드(u) 탐색
        for (int u : lists[v]) {
            // 다음 레벨이 아닌 경우(나보다 다음 노드가 아닌 경우) 패쓰
            if (level[u] != level[v] + 1) continue;

            /// 현재 노드(v)에서 다음 노드(u)로 가는데
            // 이미 최대 용량만큼 유량이 흐르고 있으면 패쓰
            if (capacity[v][u] - flow[v][u] <= 0) continue;

            // 다음 노드(u)에서부터 흐를 수 있는 유량 구해서 결과값 받음
            // amount 인자 넘겨줄 때, 현재 노드까지 흐를 수 있는 유량(amount)과
            // 다음 노드로 가는데 흐를 수 있는 유량(capacity[v][u] - flow[v][u])
            // 둘 중에 더 작은 유량으로 흐를 수 있으므로 min값 구해서 넘겨줌
            int result = getFlow(u, Math.min(amount, capacity[v][u] - flow[v][u]));

            // 유량이 흐를 수 있는 경우
            if (result > 0) {
                // 현재 노드(v)에서 다음 노드(u)로 가는 유량 업데이트 해줌
                flow[v][u] += result;
                // 유량에는 대칭성이 있으므로
                // 한쪽에서 +유량이 흐르면 반대쪽에서는 -유량이 흐른다고 생각할 수 있음
                // 다음 노드(u)에서 현재 노드(v)로 가는 유량도 업데이트 해줌
                flow[u][v] -= result;
                // 현재 노드(v)에서 다음 노드(u)로 가는 유량 반환
                return result;
            }
        }
        // 도착점에 가지 못하거나 더이상 흐를 유량이 없는 경우
        return 0;
    }

    public static void main(String[] args) {
        // 최대 유량 구하기
        int total = 0;  // 우리가 구할 최대 유량

        // 레벨 설정이 되는 경우(시작점(source)부터 목적지(sink)까지 유량이 흐를 수 있는 경우)에만 반복
        while (setLevel()) {
            // 시작점(source) 부터 목적지(sink)까지 가는 경로 하나에 대한 유량 구함
            // 경로에는 노드에서 노드로 가는 간선들이 있고
            // 간선들 마다 흐를 수 있는 유량이 있고
            // 해당 경로에 흐를 수 있는 유량은 간선들에 흐르는 유량들 중에서 최소값이 흐를 수 있으므로
            // 최소 유량(min)을 구하기 위해
            // amount인자 값으로 최대값을 넣어줌
            int result = getFlow(0, Integer.MAX_VALUE);
            // 더 이상 흐를 수 있는 유량이 없으면 -> 탈출
            if (result == 0) break;
            // 최대 유량 갱신해감
            total += result;
        }
    }
}