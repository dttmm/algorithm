package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11724 {

    static int[][] arr;
    static boolean[] visited;

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int n = queue.poll();

            int max_col = arr[n][0];
            for (int i = 1; i <= max_col; i++) {
                int u = arr[n][i];
                if (!visited[u]) {
                    queue.add(u);
                    visited[u] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11724.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int col = arr[from][0]++;
            arr[from][col + 1] = to;

            int col2 = arr[to][0]++;
            arr[to][col2 + 1] = from;
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                count++;
            }
        }

        System.out.println(count);
    }
}
