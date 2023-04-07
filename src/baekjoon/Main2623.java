package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 위상 정렬
 총 N번 큐를 돌면서
 도는 중에 큐가 비었다면 사이클로 판정함

 주어진 입력의 순서를 반대로 입력 받아서
 진입 차수가 0이 노드를 먼저 출력함
 */
public class Main2623 {

    static int N;
    static int M;
    static Set<Integer>[] nodes;
    static int[] in;    // 진입 차수 정보 저장
    static StringBuilder sb;

    // 위상 정렬
    static boolean solve() {
        Queue<Integer> queue = new LinkedList();

        // 진입 차수 0인 것들 담아줌
        for (int i = 1; i <= N; i++) {
            if (in[i] != 0) continue;
            queue.add(i);
        }

        for (int i = 1; i <= N; i++) {
            // 사이클 판정
            if (queue.isEmpty()) return false;

            int v = queue.poll();

            // 이웃들 방문해줌
            for (int u : nodes[v]) {
                in[u]--;
                // 진입 차수가 0이 되면 큐에 추가
                if (in[u] == 0) queue.add(u);
            }
            sb.append(v + "\n");
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2623.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new Set[N + 1];
        in = new int[N + 1];
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            nodes[i] = new HashSet();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int from = 0;
            for (int j = 0; j < k; j++) {
                int to = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    from = to;
                    continue;
                }

                if (!nodes[from].contains(to)) {
                    nodes[from].add(to);
                    in[to]++;
                }
                from = to;
            }
        }

        boolean result = solve();
        if (result) System.out.println(sb);
        else System.out.println(0);
    }
}
