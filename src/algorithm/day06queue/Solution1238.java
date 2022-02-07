package algorithm.day06queue;

import java.io.FileInputStream;
import java.util.Scanner;

// 42분
public class Solution1238 {
    static int[][] G;
    static int[] visited;
    static int[] q;
    static int f;
    static int r;
    static int max_order;
    static int max_num;

    public static void BFS(int start) {
        q[++r] = start;
        visited[start] = 1;
        while (!(f == r)) {
            int v = q[++f];
            if (visited[v] > max_order) {
                max_order = visited[v];
                max_num = v;
            } else if (visited[v] == max_order && v > max_num) {
                max_num = v;
            }

            for (int i = 1; i <= G[v][0]; i++) {
                int u = G[v][i];
                if (visited[u] == 0) {
                    visited[u] = visited[v] + 1;
                    q[++r] = u;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day06_1238.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            int start = sc.nextInt();
            G = new int[101][101];
            visited = new int[101];
            q = new int[N];
            f = -1;
            r = -1;
            max_order = 0;
            max_num = 0;

            for (int i = 0; i < N / 2; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                G[from][++G[from][0]] = to;
            }

            BFS(start);
            System.out.println("#" + test_case + " " + max_num);
        }
    }
}