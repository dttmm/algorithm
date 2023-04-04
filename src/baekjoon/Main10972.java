package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 일단 순열을 만드는 함수(solve)를 이용해서
 주어진 입력대로 순열을 만든다음
 다음 순열을 만들었음

 solve함수 돌릴때 flag를 이용해서
 현재 입력 순열을 만들 차례인지 다은 순열을 만들 차례인지 구분함
 */
public class Main10972 {

    static int N;
    static int[] arr;
    static int[] tr;
    static boolean[] visited;
    static int flag;    // 1: 입력 순열을 만든 경우 2: 다음 순열을 만든 경우
    static StringBuilder sb;

    static void solve(int k) {
        if (k == N) {
            // 다음 순열을 만들 차례라면
            if (flag == 1) {
                for (int i = 0; i < N; i++) {
                    sb.append(tr[i] + " ");
                }
            }
            flag++;
        } else {
            int n;
            // 입력 순열 만들 차례라면
            if (flag == 0) n = arr[k];
            // 다음 순열 만들 차례라면
            else n = 1;

            for (int i = n; i <= N; i++) {
                if (visited[i]) continue;
                visited[i] = true;
                tr[k] = i;
                solve(k + 1);

                // 이미 다음 순열 만들었다면
                if (flag == 2) return;
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10972.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tr = new int[N];
        visited = new boolean[N + 1];
        flag = 0;
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        solve(0);

        if (flag == 2) System.out.println(sb);
        else System.out.println(-1);
    }
}
