package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main9020 {
    static int[] arr = new int[10001];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9020.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 2; i <= 10000; i++) {
            arr[i] = i;
        }

        for (int i = 2; i <= 10000; i++) {
            if (arr[i] != 0) {
                for (int j = i * 2; j <= 10000; j += i) {
                    arr[j] = 0;
                }
            }
        }

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            int N = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            int answer1 = 0;
            int answer2 = 0;
            for (int i = 2; i < N; i++) {
                if (arr[i] != 0) {
                    int other = N - i;
                    if (arr[other] != 0) {
                        if (Math.abs(i - other) < min) {
                            min = Math.abs(i - other);
                            answer1 = i > other ? other : i;
                            answer2 = i > other ? i : other;
                        }
                    }
                }
            }
            System.out.println(answer1 + " " + answer2);
        }
    }
}
