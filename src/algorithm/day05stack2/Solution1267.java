package algorithm.day05stack2;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1267 {
    static int[][] arr;
    static boolean[] visited;
    static int V;


    public static void DFS(int v) {
        if (!visited[v]) {
            for (int i = 0; i < V; i++) {
                if (arr[v][i] == 1 && !visited[i]) {
                    DFS(i);
                }
            }
        }
        visited[v] = true;
        System.out.print(1 + v + " ");
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day05_1267.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            V = sc.nextInt();
            int E = sc.nextInt();
            arr = new int[V][V];
            visited = new boolean[V];
            for (int i = 0; i < E; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                arr[to - 1][from - 1] = 1;
            }
            System.out.print("#" + test_case + " ");
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    DFS(i);
                }
            }
            System.out.println();
        }
    }
}