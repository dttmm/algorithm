package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main1799 {

    static int N;
    static int[][] arr;
    static int max;
    static int limitIndex;
    static boolean[] visited1;
    static boolean[] visited2;
    static List<Integer> list;

    static void solve(int listIndex, int count) {
        if (listIndex == list.size()) {
            max = Math.max(max, count);
            return;
        }

        int index = list.get(listIndex);
        int i = index / N;
        int j = index % N;

        if (arr[i][j] == 1) {
            int index1 = N - i + j - 1;
            int index2 = i + j;

            if (!visited1[index1] && !visited2[index2]) {
                visited1[index1] = true;
                visited2[index2] = true;

                solve(listIndex + 1, count + 1);

                visited1[index1] = false;
                visited2[index2] = false;
            }
        }
        solve(listIndex + 1, count);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1799.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        max = 0;
        limitIndex = N * N;
        visited1 = new boolean[2 * N - 1];
        visited2 = new boolean[2 * N - 1];
        list = new ArrayList();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;

                if (n == 1) list.add(i * N + j);
            }
        }

        solve(0, 0);

        System.out.println(max);
    }
}
