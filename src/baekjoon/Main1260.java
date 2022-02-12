package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1260 {
    static int[][] arr;
    static boolean[] visited;
    static int[] s;
    static int[] q;
    static int top;
    static int front;
    static int rear;
    static int N;

    public static void bfs(int start) {
        q[++rear] = start;
        visited[start] = true;
        while (front != rear) {
            int v = q[++front];
                System.out.print(v + " ");
                for (int u = 0; u <=N; u++) {
                    if (arr[v][u] == 1 && !visited[u]) {
                        q[++rear] = u;
                        visited[u] = true;
                }
            }
        }
    }

    public static void dfs(int start) {
        s[++top] = start;
        while (top != -1) {
            int v = s[top--];
            if (!visited[v]) {
                System.out.print(v + " ");
                visited[v] = true;
                for (int u = N; u > 0; u--) {
                    if (arr[v][u] == 1 && !visited[u]) {
                        s[++top] = u;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1260.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        s = new int[10000];
        q = new int[10000];
        top = -1;
        front = -1;
        rear = -1;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            arr[from][to] = 1;
            arr[to][from] = 1;
        }
        dfs(start);
        System.out.println();
        visited = new boolean[N + 1];
        bfs(start);
    }
}
