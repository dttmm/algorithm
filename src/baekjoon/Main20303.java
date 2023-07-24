package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 17분 구현 24분 디버깅 17분
 bfs + dp로 접근함
 bfs를 이용하여 연결할 수 있는 노드를 연결하고
 연결된 노드의 개수(child)와 연결된 노드의 사탕 총합(candy)을 담는
 Node클래스를 만들고 nodes리스트에 저장해서 연결정보를 관리함
 그 다음은 배낭문제처럼 이차원 dp를 이용하여 dp로 정답을 구함
 i행에는 하나의 노드,
 j열에는 가능한 최대 아이들의 수로 두고
 하나의 노드를 선택하였을때
 j명의 아이들의 사탕을 뺏을 수 있는 경우를 구해나감

 틀림
 dp배열을 갱신하는 부분에서 틀림
 현재 노드의 아이들의 수가 j명보다 작을 때 dp배열을 갱신할 수 있도록 했는데
 현재 노드의 아이들의 수와 이전 최대값에서 가능한 아이들의 수의 합도 j명보다 작아야 되는 것을 간과함

 시간초과남
 아이들의 연결 정보를 구하고
 구한 정보를 바탕으로 dp를 이용하여 최대값을 구하는 로직에는 문제가 없어보임
 최적화를 할 방법이 떠오르지 않아서
 도움!

 아
 이차원 dp관리할 때
 자료형을 Node로 했는데
 Node를 복사하고 Node끼리 값을 비교하는 과정에서 시간이 많이 걸리는군하
 자료형을 int로 하니까 시간초과 안나네
 그래도 내가 설계한 로직이 틀리지 않아서 다행쓰
 2치원 dp와 1차원 dp의 차이가 많이 나네
 메모리는 10배정도 시간은 1.8배 정도 차이남

 1차원 dp는 뒤에서 부터 갱신을 해야 갱신 중복을 막을 수 있군하

 K명 미만의 아이들을 건드려야 되니까
 그냥 K-1명까지 dp테이블을 구하면 되는 군하
 */
public class Main20303 {

    static int N;
    static int M;
    static int K;
    static int[] candies;           // 각 아이들이 갖고 있는 사탕 개수
    static List<Integer>[] lists;   // 아이들 연결 정보
    static boolean[] visited;
    static List<Node> nodes;        // 연결되어 있는 아이들 그룹 정보(아이들 수, 사탕 수)
    static int[] d;                 // dp 배열

    // 연결되어 있는 아이들 그룹 정보
    private static class Node {
        int child;  // 그룹에 포함된 아이들의 수
        int candy;  // 그룹에 있는 아이들이 갖고 있는 사탕 개수 총합

        private Node(int child, int candy) {
            this.child = child;
            this.candy = candy;
        }
    }

    // 아이들 연결해줌
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        visited[start] = true;

        int child = 1;
        int candy = candies[start];
        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int u : lists[v]) {
                if (visited[u]) continue;

                queue.add(u);
                visited[u] = true;
                child++;
                candy += candies[u];
            }
        }

        // 아이들 그룹 정보 추가
        nodes.add(new Node(child, candy));
    }

    // dp
    static void solve() {
        d = new int[K];

        // 하나의 노드(그룹)을 선택해 나갔을 때
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);

            // 가능한 최대 사탕 개수 업데이트
            // 1차원 dp이기에 뒤에서부터 값 갱신
            for (int j = K - 1; j >= node.child; j--) {
                d[j] = Math.max(d[j], d[j - node.child] + node.candy);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/20303.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candies = new int[N + 1];
        lists = new List[N + 1];
        visited = new boolean[N + 1];
        nodes = new ArrayList();

        // 초기화
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList();
        }

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            candies[i] = n;
        }

        // 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            lists[n1].add(n2);
            lists[n2].add(n1);
        }

        // 아이들 연결하여 그룹 만들기
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            bfs(i);
        }

        // dp테이블 갱신
        solve();

        System.out.println(d[K - 1]);
    }
}
