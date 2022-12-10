package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 DFS로 모든 경로르 탐색하면서 리프 토드에 도달했을 때
 지금까지의 합과 max값을 비교하려고 했는데 시간초과가 났다

 이번에는 한줄 한줄씩 탐색하면서
 윗 줄에서 왼쪽과 오른쪽 중 큰 수를 선택해서
 자신하고 더해주고 arr을 더한 숫자로 갱신시켰다
 리프노드인 경우에는 갱신된 숫자와 max값을 비교하였다

 처음 방법과 나중 방법에서의 solve함수 호출 횟수를 계산해 보았다
 처음 방법은 2^n 만큼의 연산이 발생하였다
 나중 방법은 n*(n+1)/2 만큼의 연산이 발생하였다
 */
public class Main1932 {

    static int N;
    static int[][] arr;
    static int max;

    static void solve(int k) {
        for (int index = 0; index <= k; index++) {

            int cur_num = arr[k][index];
            if (index == 0) {
                arr[k][index] = cur_num + arr[k - 1][index];
            } else if (index == k) {
                arr[k][index] = cur_num + arr[k - 1][k - 1];
            } else {
                arr[k][index] = Math.max(cur_num + arr[k - 1][index - 1], cur_num + arr[k - 1][index]);
            }

            if (k == N - 1) max = Math.max(max, arr[k][index]);

        }
        if (k == N - 1) return;
        solve(k + 1);
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1932.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = 0;

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        if (N == 1) {
            System.out.println(arr[0][0]);
        } else {
            solve(1);
            System.out.println(max);
        }
    }
}
