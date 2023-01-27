package baekjoon;

import java.io.*;
import java.util.*;

/**
 bfs로 풀음
 각 노드의 연결 정보를 lists에 저장하고
 루트 노드인 1부터 시작하여 내려가면서
 연결되어 있는 노드를 탐색하여 부모 정보를 업데이트 해줌
 */
public class Main11725 {

    static int N;
    static List<Integer>[] lists;
    static int[] p;

    static void solve() {
        Queue<Integer> queue = new LinkedList();
        queue.add(1);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            for (int u : lists[v]) {
                if (p[u] != 0) continue;

                queue.add(u);
                p[u] = v;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11725.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        lists = new List[N + 1];
        p = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            lists[n1].add(n2);
            lists[n2].add(n1);
        }

        solve();

        for (int i = 2; i <= N; i++) bw.write(p[i] + "\n");
        bw.flush();
        bw.close();
    }
}
