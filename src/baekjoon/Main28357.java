package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 4분 구현 8분 디버깅 1분
 이분탐색
 오랜만에 구현하려니까
 어느 부분에서 정답을 갱신시켜야 하는지
 어떤값을 최대값 혹은 최소값으로 갱신시켜야 하는지 좀 헷갈렸음

 틀림
 입력 받을 때 Long형으로 받기 주의...
 */
public class Main28357 {

    static int N;
    static long K;
    static long[] arr;  // 점수들
    static long answer;

    // 이분 탐색
    static void solve(long start, long end) {
        if (start > end) return;

        long mid = (start + end) / 2;

        // 나누어 줄 수 있는 사탕 총합 구하기
        long sum = 0;
        for (int i = 0; i < N; i++) {
            long score = arr[i];
            if (score < mid) continue;

            sum += score - mid;
            if (sum > K) break;
        }

        // 나누어 줄 사탕이 예산을 넘은 경우 -> 사탕 줄 수 있는 기준을 높여야 됨
        if (sum > K) {
            solve(mid + 1, end);
        }
        // 예산 안에서 사탕을 나누어 줄 수 있는 경우 -> 사탕 줄 수 있는 기준 낮춰도 됨
        else {
            answer = Math.min(answer, mid);
            solve(start, mid - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28357.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        arr = new long[N];
        answer = Long.MAX_VALUE;

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long n = Long.parseLong(st.nextToken());
            arr[i] = n;
        }

        // 이분 탐색
        solve(0, 1000000000000L);

        System.out.println(answer);
    }
}
