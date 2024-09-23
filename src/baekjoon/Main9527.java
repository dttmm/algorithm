package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main9527 {

    static long[] arr;
    static long[] sum;

    static long solve(int k, long n) {
        long rest = n % 2;

        long total = 0;
        if (n / 2 != 0) {
            total += solve(k + 1, n / 2);
        }

        if (rest == 1) return total + sum[k];
        else return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9527.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        arr = new long[60];
        sum = new long[60];

        arr[2] = 1;
        sum[2] = 1;
        long total = 1;
        for (int i = 3; i < 60; i++) {
            total = total * 2;
            arr[i] = (i - 1) * total;
            sum[i] = sum[i - 1] + arr[i];
        }

        long sumA = solve(1, a);
        long sumB = solve(1, b);

        System.out.println(sumB - sumA);
    }
}
