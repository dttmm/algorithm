package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11053 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11053.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[1001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            int max = 0;
            for (int j = n - 1; j >= 0; j--) {
                max = Math.max(max, arr[j]);
            }

            arr[n] = max + 1;
        }

        int max = 0;
        for (int i = 1; i <= 1000; i++) {
            max = Math.max(max, arr[i]);
        }

        System.out.println(max);
    }
}
