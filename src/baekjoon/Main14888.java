package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14888 {
    static int N;
    static int[] arr;
    static int[] operator;
    static int[] tr;
    static int max;
    static int min;

    public static void p(int k) {
        if (k == N - 1) {
            int result = arr[0];
            for (int i = 0; i < N - 1; i++) {
                int op = tr[i];
                int n = arr[i + 1];
                if (op == 0) {
                    result += n;
                } else if (op == 1) {
                    result -= n;
                } else if (op == 2) {
                    result *= n;
                } else {
                    result /= n;
                }
            }
            if (result < min) min = result;
            if (result > max) max = result;
        } else {
            for (int i = 0; i < 4; i++) {
                if (operator[i] != 0) {
                    operator[i]--;
                    tr[k] = i;
                    p(k + 1);
                    operator[i]++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14888.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        operator = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        int idx = 0;
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        tr = new int[N - 1];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        p(0);
        System.out.println(max);
        System.out.println(min);

    }
}