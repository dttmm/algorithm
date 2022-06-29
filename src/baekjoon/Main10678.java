package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main10678 {
    static int N;
    static int M;
    static int[][] arr_B;
    static int[][] arr_E;

    public static void solve(int[][] arr, int v, int cost, List<Integer> nodes) {
        for (int w = 1; w <= N; w++) {
            int n = arr[v][w];
            if (n != 0 && w > v) {
                int newCost = cost + n;

                if (w == N) nodes.add(newCost);

                solve(arr, w, newCost, nodes);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10678.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr_B = new int[N + 1][N + 1];
        arr_E = new int[N + 1][N + 1];
        List<Integer> nodes_B = new ArrayList();
        List<Integer> nodes_E = new ArrayList();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            arr_B[from][to] = B;
            arr_E[from][to] = E;
        }

        solve(arr_B, 1, 0, nodes_B);
        solve(arr_E, 1, 0, nodes_E);

        Collections.sort(nodes_B);
        Collections.sort(nodes_E);

        String result = "IMPOSSIBLE";
        boolean flag = false;

        for (int cost1 : nodes_B) {
            for (int cost2 : nodes_E) {
                if (cost1 == cost2){
                    result = cost1 + "";
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        System.out.println(result);
    }
}
