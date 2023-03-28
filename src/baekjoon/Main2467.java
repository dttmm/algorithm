package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 투포인터를 이용함
 먼저 입력을 정렬한 뒤
 양 끝(s, e)에서부터 값을 더함(sum)
 만약 sum이 양수이면 e값을 하나 줄여서 sum을 줄일도록 하고
 sum이 음수이면 s값을 하나 높여서 sum을 높이도록하여
 0에 가까운 조합을 찾음
 */
public class Main2467 {

    static int N;
    static int[] arr;
    static int[] answer;
    static long min;

    static void solve(int s, int e) {
        if (s >= e) return;

        long sum = (long) arr[s] + arr[e];

        // 합이 양수인 경우
        if (sum > 0) {
            // 0에 더 가까우면 정답 갱신
            if (Math.abs(sum) < min) {
                answer[0] = arr[s];
                answer[1] = arr[e];
                min = Math.abs(sum);
            }

            // 더 작은 숫자로 다시 계산
            solve(s, e - 1);
        }
        // 합이 음수인 경우
        else if (sum < 0) {
            // 0에 더 가까우면 정답 갱신
            if (Math.abs(sum) < min) {
                answer[0] = arr[s];
                answer[1] = arr[e];
                min = Math.abs(sum);
            }

            // 더 큰 숫자로 다시 계산
            solve(s + 1, e);
        }
        // 합이 0인 경우
        else {
            answer[0] = arr[s];
            answer[1] = arr[e];
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2467.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[2];
        min = Long.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        Arrays.sort(arr);
        solve(0, N - 1);

        System.out.println(answer[0] + " " + answer[1]);
    }
}
