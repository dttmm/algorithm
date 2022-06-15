package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main18511 {
    static int N;
    static int K;
    static int[] arr;
    static int max;
    static boolean flag;
    static int len;

    public static void p(int k, int num) {
        if (num > N) return;

        if (k == len) {
            max = Math.max(max, num);
            flag = true;
        } else {
            for (int i = 0; i < K; i++) {
                int n = arr[i];
                int new_num = num + n * (int) Math.pow(10, len - k - 1);
                p(k + 1, new_num);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/18511.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        max = 0;
        flag = false;
        len = String.valueOf(N).length();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        p(0, 0);

        if (!flag) {
            int int_max = 0;

            for (int i = 0; i < K; i++) {
                int_max = Math.max(int_max, arr[i]);
            }

            for (int i = 1; i < len; i++) {
                max += int_max * (int) Math.pow(10, len - i - 1);
            }
        }

        System.out.println(max);
    }
}
