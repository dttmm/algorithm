package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 넘모 어렵다
 dp 규칙을 찾기 위해 삽질을 했지만
 규칙 찾기 실패
 결국 도움!

 새로 추가된 색을 선택할지 말지
 두 경우로 나눌 수가 있구나

 색이 순환하는 구조다 보니
 첫번째 색을 선택할지 말지에 따라
 또 처음부터 경우가 나뉘는 군하
 */
public class Main2482 {

    final static int R = 1000000003;
    static int N;
    static int K;
    static int[][] d;

    static int solve(int n, int k) {
        if (d[n][k] != -1) return d[n][k];
        if (k > n) return d[n][k] = 0;
        if (k == 0) return d[n][k] = 1;
        if (n == 1) return d[n][k] = k;

        // 새로 추가된 색을 선택하지 않는 경우 + 선택하는 경우
        d[n][k] = (solve(n - 1, k) + solve(n - 2, k - 1)) % R;

        return d[n][k];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2482.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        d = new int[N + 1][K + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                d[i][j] = -1;
            }
        }

        // 첫 번째 색을 선택하는 경우 + 첫 번째 색을 선택하지 않는 경우
        int answer = (solve(N - 3, K - 1) + solve(N - 1, K)) % R;
        System.out.println(answer);
    }
}
