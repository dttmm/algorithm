package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 완탐으로 풀음
 한번 먹이를 먹기 시작하면 K가 될때까지 먹고
 이전에 먹이를 먹은 경우가 없으면
 먹이를 먹든지 안먹든지 2 가지 경우로 선택하여 재귀함
 끝에 도달했을 경우 지금까지 축적된 에너지 최대값 비교함
 */
public class Main20167 {

    static int N;
    static int K;
    static int[] arr;
    static int max;

    static void solve(int k, int sum, int energy) {
        // 지금까지 먹은 먹이가 K이상인 경우
        if (sum >= K) {
            energy += sum - K;
            sum = 0;
        }

        // 끝까지 다 도달한 경우
        if (k == N) {
            max = Math.max(max, energy);
            return;
        }

        // 계속 먹이를 먹으면서 진행
        solve(k + 1, sum + arr[k], energy);

        // 먹이를 먹지않고 진행
        if (sum == 0) solve(k + 1, sum, energy);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/20167.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        max = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        solve(0, 0, 0);

        System.out.println(max);
    }
}
