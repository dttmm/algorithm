package 공부.강한결합요소_StronglyConnectedComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Tarjan {
    // 모든 정점에 대해 DFS를 수행하며 SCC를 찾는 알고리즘
    // 부모와 자식 관계를 이용하여 SCC를 찾음
    // 부모로 다시 돌아올 수 있어야 SCC가 성립 됨

    static int N;               // 노드 개수
    static Stack<Integer> stack;
    static int[] p;             // 노드 부모
    static boolean[] finished;  // DFS가 진행중인지 끝났는지
    static List<List<Integer>> sccList; // SCC들 저장할 리스트
    static List<Integer>[] lists;

    static int solve(int v) {
        // 자기 자신 부모로 설정
        p[v] = v;
        // 스택에 넣기
        stack.push(v);

        int parent = p[v];
        // 연결된 이웃 확인
        for (int u : lists[v]) {
            // 방문한 적이 없는 경우
            // -> DFS 수행하며 더 작은 부모 찾음
            if (p[u] == 0) parent = Math.min(parent, solve(u));

                // 방문했는데 아직 DFS 수행중인 경우
                // -> 처리되고 있는 값의 부모와 비교
                // DFS가 되고 있는 노드에는 또 DFS를 수행할 수 없으니까
                // 현재까지 낭노 부모중에서 작은 값 찾음
            else if (!finished[u]) parent = Math.min(parent, p[u]);
        }

        // 모든 노드 다 확인한 후

        // 부모 노드가 자기 자신인 경우
        // -> 자신이 나올 때까지 스택에서 pop해서 SCC 만듦
        if (parent == p[v]) {
            // SCC정보 담을 리스트
            List<Integer> scc = new ArrayList();

            while (true) {
                // 스택에서 하나 꺼내서
                int n = stack.pop();
                // SCC에 추가해주고
                scc.add(n);
                // 처리 완료 처리 해줌
                finished[n] = true;
                // 자기 자신이 나오면 탈출
                if (n == v) break;
            }

            // 만들어진 하나의 SCC를
            // 전체 SCC정보에 추가
            sccList.add(scc);
        }

        // 자신의 부모값 반환
        return parent;
    }

    public static void main(String[] args) {

        for (int i = 1; i <= N; i++) {
            // 이미 방문했으면 패쓰
            if (p[i] != 0) continue;
            solve(i);
        }
    }
}
