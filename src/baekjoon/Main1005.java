package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 7분 구현 15분
 위상정렬에 아이디어 더한 문제
 위상정렬을 수행하면서
 각 노드에 갈 때 걸리는 시간을 최대값으로 갱신해 가면서 풀음
 */
public class Main1005 {

    static int N;
    static int K;
    static int W;
    static int[] d; // 각 노드의 건설 시간
    static List<Integer>[] lists;   // 노드 연결 정보 저장
    static long[] time; // 각 노드로 가는데 걸리는 최대 시간
    static int[] in;    // indegree 저장

    // 위상 정렬
    static void solve() {
        Queue<Integer> queue = new LinkedList();
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                queue.add(i);
                time[i] = d[i];
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : lists[v]) {
                if (in[u] == 0) continue;
                in[u]--;

                // 해당 노드로 가는데 걸리는 시간 갱신
                time[u] = Math.max(time[u], time[v] + d[u]);
                if (in[u] == 0) queue.add(u);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1005.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            d = new int[N + 1];
            lists = new List[N + 1];
            time = new long[N + 1];
            in = new int[N + 1];

            // 건설 시간 입력 받기 & 초기화
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int n = Integer.parseInt(st.nextToken());
                d[i] = n;
                lists[i] = new ArrayList();
            }

            // 입력 받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                lists[from].add(to);
                in[to]++;
            }

            W = Integer.parseInt(br.readLine());

            solve();

            sb.append(time[W] + "\n");
        }
        System.out.println(sb);
    }
}
