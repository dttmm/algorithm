package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2606 {
    static int[][] arr;
    static boolean[] visited;
    static int count;
    static Stack<Integer> st;
    static int N;

    static public void dfs() {
        st.push(1);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visited[v]) {
                visited[v] = true;
                count++;
                for (int u = 1; u <= N; u++) {
                    if (arr[v][u] == 1 && !visited[u]) {
                        st.push(u);
                    }
                }
            }
        }
    }

    //재귀버전
    static public void dfs(int v) {
        if (!visited[v]) {
            visited[v] = true;
            count++;
            for (int u = 1; u <= N; u++) {
                if (arr[v][u] == 1 && !visited[u]) {
                    dfs(u);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2606.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        count = -1;
        st = new Stack<>();

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = 1;
            arr[to][from] = 1;
        }

        dfs(1);
        System.out.println(count);
    }
}
