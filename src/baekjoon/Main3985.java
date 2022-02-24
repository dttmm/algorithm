package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3985 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3985.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int[] cake = new int[L + 1];
        int[] count = new int[N + 1];
        int[] count_real = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j = start; j <= end; j++) {
                if (cake[j] == 0) {
                    cake[j] = i;
                    count_real[i]++;
                }
                count[i]++;
            }
        }
        int max_idx = 1;
        for (int i = 2; i <= N; i++) {
            if (count[i] > count[max_idx]) {
                max_idx = i;
            }
        }
        System.out.println(max_idx);

        max_idx = 1;
        for (int i = 2; i <= N; i++) {
            if (count_real[i] > count_real[max_idx]) {
                max_idx = i;
            }
        }
        System.out.println(max_idx);
    }
}
