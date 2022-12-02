package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 내일 코테를 위해 연습해볼겸 풀어봄
 dfs와 bfs의 방문처리 시점이 다른 것이 포인트
 */

public class Main1260_3 {

    static int N;
    static int M;
    static LinkedList<Integer>[] list;
    static boolean[] visited;
    static StringBuilder sb;

    static void dfs(int start) {
        Stack<Integer> stack = new Stack();
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.pop();

            if (!visited[v]) {
                visited[v] = true;
                sb.append(v + " ");

                PriorityQueue<Integer> pq = new PriorityQueue();

                for (int u : list[v]) {
                    if (!visited[u]) {
                        pq.add(u * -1);
                    }
                }

                while (!pq.isEmpty()) {
                    int u = pq.poll();
                    stack.push(u * -1);
                }
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        queue.add(start);
        visited[start] = true;
        sb.append(start + " ");

        while (!queue.isEmpty()) {
            int v = queue.poll();

            PriorityQueue<Integer> pq = new PriorityQueue();

            for (int u : list[v]) {
                if (!visited[u]) {
                    pq.add(u);
                    visited[u] = true;
                }
            }

            while (!pq.isEmpty()) {
                int u = pq.poll();
                queue.add(u);
                sb.append(u + " ");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1260.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        list = new LinkedList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new LinkedList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            list[n1].add(n2);
            list[n2].add(n1);
        }

        visited = new boolean[N + 1];
        dfs(start);

        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(start);


        System.out.println(sb);
    }
}
