package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2748 {
    static long[] d;

    public static long f(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (d[(int) n] != 0) return d[(int) n];
        return d[(int) n] = f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2748.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        d = new long[91];
        System.out.println(f(Integer.parseInt(br.readLine())));
    }
}
