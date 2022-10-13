package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11047 {

    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11047.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int sum = 0;
        while (K != 0) {
            int target = arr[0];
            for (int i = 0; i < N; i++) {
                if (arr[i] <= K) target = arr[i];
                else break;
            }

            sum += K / target;
            K %= target;
        }

        System.out.println(sum);
    }
}
