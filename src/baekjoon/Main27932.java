package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 이분 탐색으로
 최적의 키 차이 찾음

 배열 탐색 쉽게 하기 위해서
 배열 크기를 N+2로 양 옆 공간 확보해놓고
 1~N까지 탐색하면서 양 옆 원소값 비교해줌

 아 키차이가 0이 될 수도 있었군하...
 */
public class Main27932 {

    static int N;
    static int K;
    static int[] arr;
    static int min; // 지친 사람이 K명 이하가 되기 위한 최소 키 차이

    // 이분 탐색
    static void solve(int start, int end) {
        if (start > end) return;
        int mid = (start + end) / 2;

        // 양 옆의 키 차이를 계산
        int count = 0;  // 지친 사람 카운트
        for (int i = 1; i <= N; i++) {
            // 왼쪽하고 비교
            if (Math.abs(arr[i] - arr[i - 1]) > mid) {
                // 키 차이가 mid 초과하면 -> 지친 개수 카운트
                count++;
                if (count > K) break;
                continue;
            }
            // 오른쪽하고 비교
            if (Math.abs(arr[i + 1] - arr[i]) > mid) {
                count++;
                if (count > K) break;
            }
        }

        // 지친 사람이 K명을 넘었을 경우 -> 검사할 키 높여
        if (count > K) solve(mid + 1, end);
        // 지친 사람이 K명 이하인 경우 -> 검사할 키 낮춰
        else {
            min = Math.min(min, mid);
            solve(start, mid - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/27932.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 2];
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 배열 양 끝 확장
        arr[0] = arr[1];
        arr[N + 1] = arr[N];

        solve(0, 1000000000);
        System.out.println(min);
    }
}
