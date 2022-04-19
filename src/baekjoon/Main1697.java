package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1697 {
    static int N;
    static int K;
    static int[] visited;
    static Queue<Integer> queue;

    public static boolean isIn(int newI) {
        if (newI >= 0 && newI < 100001) return true;
        return false;
    }

    public static void solve(int start) {
        queue.add(start);
        queue.add(-1);
        queue.add(start);
        queue.add(0);
        queue.add(start);
        queue.add(1);

        while (!queue.isEmpty()) {
            start = queue.poll();
            int dir = queue.poll();
            if (start == K) break;
            switch (dir) {
                case -1: {
                    int newI = start - 1;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(-1);
                    }

                    newI = start * 2;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(0);
                    }
                    break;

                }
                case 0: {
                    int newI = start - 1;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(-1);
                    }

                    newI = start * 2;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(0);
                    }

                    newI = start + 1;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(1);
                    }
                    break;
                }
                case 1: {
                    int newI = start * 2;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(0);
                    }

                    newI = start + 1;
                    if (isIn(newI) && visited[newI] == 0) {
                        visited[newI] = visited[start] + 1;
                        queue.add(newI);
                        queue.add(1);
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1697.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new int[100001];
        queue = new LinkedList<>();

        visited[N] = 1;
        solve(N);

        System.out.println(visited[K] - 1);
    }
}
