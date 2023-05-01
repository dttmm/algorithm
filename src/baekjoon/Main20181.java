package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 행렬곱셈연산 문제처럼
 구간을 쪼개가면 구간별 최대값을 구했음
 근데 N이 너무 커서 시간초과남

 dp를 어떻게 적용해야 될까 고민하다가
 아이디어 안떠올라서 힌트봄
 이분탐색이라..
 힌트 봐도 생각안나서
 한숨 자고왔더니 유레ㅔ카!!

 d에는 현재까지의 최대값 저장하고
 현재 인덱스(index)에서 이전 인덱스들 중에서
 누적합이 K 이상이 되는 지점(mid)을 찾으면
 해당 지점과 현재 인덱스까지의 축적한 에너지양과
 해당 지점의 이전 지점에서의 최대값을 합한 값과
 현재 인덱스 이전의 최대값을 비교해서
 d값 갱신을 시켜줌

 단, 이전 인덱스에서 해당 지점까지의 구간합이
 K이상인 경우에는
 현재 인덱스까지 올 수 없으니까 (구간합이 K이상이면 에너지 축적 후 모인 에너지 초기화됨)
 해당 경우 고려해서 d값 갱신시켜줌
 */
public class Main20181 {

    static int N;
    static int K;
    static int[] arr;
    static long[] sum;  // 누적합
    static long[] d;    // i번째 까지의 최대값

    // 이분 탐색
    static void solve(int start, int end, int index) {
        if (start > end) return;

        int mid = (start + end) / 2;

        // 현재 인덱스 지점과 mid사이의 구간 합
        long diff = sum[index] - sum[mid - 1];

        // 구간합이 K 미만인 경우 -> 범위 더 늘려
        if (diff < K) solve(start, mid - 1, index);

        // 구간합이 K 이상인 경우 -> 정답을 갱신하자
        else {
            // 해당 구간이 고를 수 있는 구간인 경우
            if (sum[index - 1] - sum[mid - 1] < K) {
                // 해당 지점부터 현재 인덱스까지 축적된 에너지 + 해당 지점 이전의 최대값
                long max = Math.max(d[index - 1], diff - K + d[mid - 1]);
                // 최대값 갱신
                d[index] = Math.max(d[index], max);

                // 범위 더 늘려서 가능한 최대값 더 찾자
                solve(start, mid - 1, index);
            }
            // 이전 인덱스 지점과 mid사이의 구간 합이 K를 넘긴 경우 -> 고를 수 없는 구간
            else {
                // 이전 최대값과 비교
                d[index] = Math.max(d[index], d[index - 1]);

                // 범위 좁혀서 가능한 구간 찾자
                solve(mid + 1, end, index);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/20181.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        sum = new long[N + 1];
        d = new long[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            sum[i] = sum[i - 1] + n;
        }

        // 바텀업
        for (int i = 1; i <= N; i++) {
            solve(1, i, i);
        }

        System.out.println(d[N]);
    }
}
