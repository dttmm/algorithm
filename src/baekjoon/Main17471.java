package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17471 {
    static int N;
    static int[] arr;
    static int[][] G;
    static int[] area;
    static boolean[] visited;
    static int min;
    static int total;

    public static int bfs(int k) {
        int count = 0;
        int[] q = new int[100];
        int front = -1;
        int rear = -1;
        q[++rear] = k;
        visited[k] = true;
        count += arr[k];
        while (front != rear) {
            k = q[++front];
            for (int w = 1; w < N + 1; w++) {
                if (G[k][w] == 1 && !visited[w] && area[k] == area[w]) {
                    q[++rear] = w;
                    visited[w] = true;
                    count += arr[w];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17471.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        total = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            total += n;
        }
        G = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                int to = Integer.parseInt(st.nextToken());
                G[i][to] = 1;
            }
        }

        min = Integer.MAX_VALUE;
        for (int i = 1; i < (1 << N) - 1; i++) {
            area = new int[N + 1];
            boolean flag = true;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    area[j + 1] = 1;
                } else {
                    area[j + 1] = 2;
                }
            }
            visited = new boolean[N + 1];
            int times = 0;
            int count = 0;
            int reverse_count = 0;
            for (int k = 1; k <= N; k++) {
                if (!visited[k]) {
                    count = bfs(k);
                    reverse_count = total - count;
                    times++;
                    if (times == 3) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                if (Math.abs(count - reverse_count) < min) min = Math.abs(count - reverse_count);
            }
        }
        if (min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);
    }
}
