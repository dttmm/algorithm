package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 구현 10분
 어제 배운 SCC 적용해서 문제 풀어봄
 리스트를 저장하고 있는 리스트를 정렬해야 되길래 순간 헉! 했음
 Comparator사용해서 정렬 규칙 정함
 */
public class Main2150 {

    static int N;
    static int M;
    static List<Integer>[] lists;
    static Stack<Integer> stack;
    static int[] p;
    static boolean[] finished;
    static int id;
    static List<List<Integer>> sccList;

    // SCC 연결
    static int solve(int v) {
        stack.add(v);
        p[v] = id++;

        int parent = p[v];
        for (int u : lists[v]) {
            if (p[u] == 0) parent = Math.min(parent, solve(u));
            else if (!finished[u]) parent = Math.min(parent, p[u]);
        }

        if (parent == p[v]) {
            List<Integer> scc = new ArrayList();
            while (true) {
                int n = stack.pop();
                finished[n] = true;
                scc.add(n);
                if (n == v) break;
            }
            Collections.sort(scc);
            sccList.add(scc);
        }

        return parent;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2150.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lists = new List[N + 1];
        stack = new Stack();
        p = new int[N + 1];
        finished = new boolean[N + 1];
        id = 1;
        sccList = new ArrayList();

        // 초기화
        for (int i = 1; i <= N; i++) {
            lists[i] = new ArrayList();
        }

        // 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lists[from].add(to);
        }

        // 아직 dfs 안한 노드 탐색
        for (int i = 1; i <= N; i++) {
            if (finished[i]) continue;
            solve(i);
        }

        // 리스트를 저장하고 있는 리스트 정렬
        Collections.sort(sccList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(0) - o2.get(0);
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append(sccList.size() + "\n");

        for (List<Integer> scc : sccList) {
            for (int n : scc) {
                sb.append(n + " ");
            }
            sb.append("-1\n");
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
