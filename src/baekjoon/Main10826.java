package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main10826 {
    static BigInteger[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10826.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        d = new BigInteger[10001];

        d[0] = BigInteger.ZERO;
        d[1] = BigInteger.ONE;

        for (int i = 2; i <= N; i++) {
            d[i] = d[i - 1].add(d[i - 2]);
        }

        System.out.println(d[N]);

    }
}
