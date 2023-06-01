package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 4분 구현 6분
 이항계수의 성질을 이용함
 1
 1 1
 1 2 1
 1 3 3 1
 위와 같이 피라미드 형태로 이항계수를 구할 수 있음
 위 모양 그래도 점화식을 도출할 수 있음
 (n, k) = (n-1, k-1) + (n-1, k)
 */
public class Main11051 {

    static final int R = 10007;
    static int N;
    static int K;
    static int[][] d;

    // dp
    static int solve(int n, int k) {
        if (d[n][k] != 0) return d[n][k];

        d[n][k] = (solve(n - 1, k) + solve(n - 1, k - 1)) % R;
        return d[n][k];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11051.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        d = new int[N + 1][N + 1];

        // 값 초기화
        for (int i = 0; i <= N; i++) {
            d[i][0] = 1;
            d[i][i] = 1;
        }

        int answer = solve(N, K);
        System.out.println(answer);
    }
}
