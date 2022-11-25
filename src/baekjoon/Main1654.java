package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1654 {

    static int K;
    static int N;
    static int[] arr;
    static long answer;

    static void solve(long start, long end) {
        if (start > end) {
            return;
        }

        long mid = (start + end) / 2;
        long result = cut(mid);

        if (result < N) {
            solve(start, mid - 1);
        } else {
            if (mid > answer) answer = mid;
            solve(mid + 1, end);
        }
    }

    static long cut(long mid) {
        long sum = 0;
        for (int i = 0; i < K; i++) {
            sum += arr[i] / mid;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1654.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[K];

        int max = 0;
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
            max = Math.max(max, n);
        }

        solve(1, max);

        System.out.println(answer);
    }
}
