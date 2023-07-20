package 공부.이분매칭_BipartiteMatching;

import java.util.List;

public class BipartiteMatching {

    // A,B 두 그룹이 나뉘어져 있는 경우에 효율적으로 사용할 수 있는 알고리즘
    // A그룹에서 B그룹으로 원하는 원소가 있을 때, 매칭 시켜줄 수 있고
    // 가장 많이 매칭 시킬 수 있는 최대 매칭을 구할 수 있음
    // DFS를 이용하여 매칭시켜줌
    // 모든 노드(V)에 대해서 연결할 수 있는 간선(E)을 찾으므로
    // 시간복잡도는 O(VE)임

    // 양 끝에 start, end 노드를 추가하고
    // 간선의 용량을 1로 설정하면 네트워크 플로우로도 풀 수 있음
    // 네트워크플로우에서 제일 빠른 Dinic 알고리즘의 시간복잡도는 O(V^2 * E)이므로
    // 매칭하는 문제에서는 이분매칭이 더 효율적이라고 할 수 있음

    static int N;
    static int M;
    static List<Integer>[] lists;   // 연결 정보 담음
    static int[] matched;           // B그룹 원소(i)와 어떤 A그룹 원소(matched[i])가 매칭 되었는지
    static boolean[] completed;     // B그룹 원소(i) 매칭 완료 여부

    // completed 초기화
    static void initCompleted() {
        completed = new boolean[M + 1];
    }

    // A그룹의 v원소 매칭 시키기
    static boolean match(int v) {
        // 아무것도 매칭 되지 않은 경우
        if (v == 0) return true;

        // 이웃 노드에 대해 매칭할 수 있는지 시도
        for (int u : lists[v]) {
            // 이미 처리한 노드는 패쓰
            if (completed[u]) continue;
            completed[u] = true;

            // 이미 해당 노드와 매칭된 노드가
            // 다른 노드와 매칭될 수 있는지 검사
            if (match(matched[u])) {
                matched[u] = v;
                return true;
            }
        }
        // 어떤한 것도 매칭 실패
        return false;
    }

    public static void main(String[] args) {

        // A그룹 원소 돌면서 매칭 시키기
        int answer = 0; // 매칭 성공한 개수
        for (int i = 1; i <= N; i++) {
            initCompleted();

            boolean result = match(i);
            if (result) answer++;
        }
    }
}
