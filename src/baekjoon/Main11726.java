package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main11726 {

    static long[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11726.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        d = new long[1001];
        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i <= N; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % 10007;
        }

        System.out.println(d[N]);
    }
}
