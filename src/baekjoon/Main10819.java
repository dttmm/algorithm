package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 정렬을 생각했음
 투포인터로 제일 큰 수와 작은 수를 골라가며 둘의 차이의 최대를 구하려고 했는데
 무조건 차가 큰 것을 고르는 것이 아니라
 연속된 배열 형태로 배치하는 것까지 고려하면
 생각할 것이 많음

 N의 크기를 보아하니
 순열로 완탐하기 충분해보여서
 완탐 문제라는 것을 깨닫!
 */
public class Main10819 {

    static int N;
    static int[] arr;
    static int[] tr;
    static boolean[] visited;
    static int max;

    static void solve(int k) {
        if (k == N) {
            int sum = 0;
            for (int i = 1; i < N; i++) {
                sum += Math.abs(tr[i] - tr[i - 1]);
            }
            max = Math.max(max, sum);
        } else {
            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;

                visited[i] = true;

                tr[k] = arr[i];
                solve(k + 1);

                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10819.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        tr = new int[N];
        visited = new boolean[N];
        max = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        solve(0);
        System.out.println(max);
    }
}
