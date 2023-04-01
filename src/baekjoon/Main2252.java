package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 스택을 이용한 dfs를 활용하려고 했지만
 깊이 검사가 다 끝나고
 sb.append 호출을 맨 마지막에 할 수가 없어서
 재귀함수로 변형하여 풀음

 그래프 순서 바꿔서 위상정렬로 풀면
 스택으로도 가능하겠군하
 */
public class Main2252 {

    static int N;
    static int M;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static StringBuilder sb;

    static void solve(int v) {

        visited[v] = true;

        for (int u : nodes[v]) {
            if (visited[u]) continue;

            solve(u);
        }
        sb.append(v + " ");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2252.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];
        visited = new boolean[N + 1];
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            nodes[from].add(to);
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            solve(i);
        }

        System.out.println(sb);
    }
}
