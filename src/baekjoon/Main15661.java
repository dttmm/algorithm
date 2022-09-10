package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main15661 {

    static int N;
    static int[][] arr;

    public static int solve() {
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < (1 << N) / 2; i++) {
            // 1인 팀과 0인팀을 담을 자료구조
            List<Integer> t1 = new ArrayList<>();
            List<Integer> t2 = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) {
                    t1.add(j);
                } else {
                    t2.add(j);
                }
            }

            // 아무도 없는 팀이 나오는 경우
            if (t1.isEmpty() || t2.isEmpty()) continue;

            int sum1 = 0;
            for (int n : t1) {
                for (int k : t1) {
                    if (n != k) {
                        sum1 += arr[n][k];
                    }
                }
            }

            int sum2 = 0;
            for (int n : t2) {
                for (int k : t2) {
                    if (n != k) {
                        sum2 += arr[n][k];
                    }
                }
            }

            int diff = Math.abs(sum1 - sum2);
            min = Math.min(min, diff);
        }

        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15661.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }
}
