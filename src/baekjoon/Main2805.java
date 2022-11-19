package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2805 {

    static int N;
    static int M;
    static int[] arr;
    static int answer;

    static long getSum(int x) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            int n = arr[i];
            if (n > x) sum += n - x;
        }
        return sum;
    }

    static void solve(int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;

        long result = getSum(mid);


        if (result == M) {
            answer = mid;
        } else if (result > M) {
            answer = mid;
            solve(mid + 1, end);
        } else {
            solve(start, mid - 1);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2805.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            max = Math.max(max, n);
        }

        solve(0, max);
        System.out.println(answer);
    }
}
