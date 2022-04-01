package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2798 {
    static int N;
    static int M;
    static int[] arr;
    static int[] tr;
    static int max;

    public static void c(int k, int s) {
        if (k == 3) {
            int total = 0;
            for (int i : tr) {
                total += i;
            }
            if (total <= M && total > max) {
                max = total;
            }
        } else {
            for (int i = s; i < N; i++) {
                tr[k] = arr[i];
                c(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2798.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        tr = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        c(0, 0);
        System.out.println(max);
    }
}
