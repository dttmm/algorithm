package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main18111 {

    static int N;
    static int M;
    static int B;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/18111.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;

                max = Math.max(max, n);
                min = Math.min(min, n);
            }
        }

        int answerTime = Integer.MAX_VALUE;
        int answerHigh = Integer.MIN_VALUE;
        // target: 목표 높이
        for (int target = min; target <= max; target++) {
            int high = 0;   // 목표 높이 보다 더 높은 블럭 개수
            int low = 0;    // 목표 높이 보다 더 낮은 블럭 개수

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int n = arr[i][j];
                    if (n > target) {
                        high += n - target;
                    } else if (n < target) {
                        low += target - n;
                    }
                }
            }

            if (high + B < low) break;

            int time = high * 2 + low;
            if (time <= answerTime) {
                answerTime = time;
                if (target > answerHigh) answerHigh = target;
            }
        }

        System.out.println(answerTime + " " + answerHigh);
    }
}
