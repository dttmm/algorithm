package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 먼저 연속합이 최대가 되는 지점을 찾은 다음
 해당 지점에서 양 옆으로 검살르 해야되나 했지만
 최대가 되는 지점이 여러개인 경우 복잡해지고
 해당 방법이 정답일 거라는 보장이 없음

 dp적 아이디어를 내기위해 짱구를 굴렸지만 실패
 도움!!

 dp 점화식을 잘못 세웎군하
 점화식이 아니라 이상한 식을 세우고 있었네
 일차원 dp로 생각해서
 N까지 오는데 가질 수 있는 연속합의 최대로 점화식을 세움
 데아터가 아래와 같다면
 10 -4 3 1 5 6 -35 12 21 -1
 dp배열은
 10 10 10 10 15 21 -14 12 33 32 가 됨
 그러면 dp[0] ~ [4]까지는 값이 같으므로
 서로 다른 부분 문제가 될 수 없어서
 하나의 큰 문제로 합쳐 질수가 없넹

 점화식을
 N에 있는 수를 포함하여 N까지 오는데 가질 수 있는 연속합의 최대로 세우면
 10 6 9 10 15 21 -14 12 33 32로 부분 문제가 잘 나누어짐

 왼쪽에서 부터의 연속합(dL)
 오른쪽에서 부터의 연속합(dR)을 구해서
 dL[i-1] + dR[i+1]을 하면은
 i를 제외한 구간의 연속합을 구할 수 있게 됨
 또한, 꼭 i를 제외할 필요 없으므로
 dL[i]도 최대값 판별에 이용함

 단, i가 배열의 처음이나 마지막인 경우는 dL[i-1] + dR[i+1]계산을 하면 안됨
 데이터가 -1밖에 없을 경우 dL[i-1] + dR[i+1] = 0이 되어버려 답이 이상하게 됨
 */
public class Main13398 {

    static int N;
    static int[] arr;
    static int[] dL;    // 왼쪽에서 부터 구한 N을 포함했을 때의 N까지의 연속합 최대값
    static int[] dR;    // 오른쪽에서 부터 구한 N을 포함했을 때의 N까지의 연속합 최대값

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13398.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 2];
        dL = new int[N + 2];
        dR = new int[N + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // dp 배열 초기화
        // 내 옆에 있는 놈에 나를 포함한 경우와 나만 포한함 경우 중 최대값 구함
        for (int i = 1; i <= N; i++) {
            dL[i] = Math.max(dL[i - 1] + arr[i], arr[i]);

            dR[N - i + 1] = Math.max(dR[N - i + 2] + arr[N - i + 1], arr[N - i + 1]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            // i를 포함했을 경우 최대값
            max = Math.max(dL[i], max);

            if (i == 1 || i == N) continue;
            // i를 포함하지 않았을 경우 최대값
            max = Math.max(dL[i - 1] + dR[i + 1], max);
        }

        System.out.println(max);
    }
}
