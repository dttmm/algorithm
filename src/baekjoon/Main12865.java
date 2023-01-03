package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 요즘 knapsack 유형이 많이 나오길래 연습겸 풀어봄
 머리로는 이해하겠는데(?) 머리로는 이해가 안돼서(?)
 유튜브에서 케이스 나누는법 보고 풀음
 Top down 방식으로 풀어봄
 n번째 아이템을 추가했을 때와 추가하지 않았을 때를 비교해가면서 풀었음

 이미 계산이 된 d인지 확인하기 위해 visited배열을 따로 만들어서 방문처리 해주었음
 다른 아이디어보니까 새로운 visited배열을 만들지 말고
 d배열을 Integer형으로 선언해서
 null인지 아닌지로 방문처리가 가능하더라
 */
public class Main12865 {

    static int N;
    static int K;
    static int[] weight;
    static int[] value;
    static int[][] d;
    static boolean[][] visited;

    // n번째 아이템을 담을건데 무게 제한이 w라는 말
    static int solve(int n, int w) {
        if (n <= 0) return 0;
        if (visited[n][w]) return d[n][w];

        // 무게제한 때문에 현재 아이템을 담지 못하는 경우
        if (w - weight[n] < 0) {
            d[n][w] = solve(n - 1, w);
        } else {
            d[n][w] = Math.max(solve(n - 1, w), solve(n - 1, w - weight[n]) + value[n]);
        }
        visited[n][w] = true;
        return d[n][w];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12865.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N + 1];
        value = new int[N + 1];
        d = new int[N + 1][K + 1];
        visited = new boolean[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            weight[i] = w;
            value[i] = v;
        }

        int result = solve(N, K);
        System.out.println(result);
    }
}
