package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 점화식 어렵다
 메모리 제한 때문에
 1차원 dp 식 세우는게 어렵다

 처음에는 3원을 만든다고 했을때
 2원을 만드는 경우 +1원 추가
 1원을 만드는 경우 +2원 추가
 위 두 경우의 합으로 생각 했는데
 그러면 12, 111, 12가 되어 12중복 발생
 중복이 언제 발생하는지 어떻게 중복을 제거할지 생각했는데
 규칙이 안보여서 못 풀었음

 1차원 dp로 동전 하나씩 사용하면서 식을 세워야 된다니
 으렵다
 */
public class Main2293 {

    static int N;
    static int K;
    static int[] arr;
    static int[] d; // 가치 i를 만드는 경우의 수

    static void solve(int n) {
        for (int i = 1; i <= K; i++) {
            // 가치 n으로 새로운 경우의 수를 만들 수 없는 경우
            if (i - n < 0) continue;

            // 이전 가치 i - n에다 n을 더하면 d[i - n]만큼 i를 만드는 경우의 수 생김
            // + 이전 동전 까지 사용했을 때, 가치 i를 만드는 경우의 수
            d[i] = d[i - n] + d[i];
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2293.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        d = new int[K + 1];
        d[0] = 1;

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            solve(n);
        }

        System.out.println(d[K]);
    }
}
