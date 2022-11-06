package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1463 {

    static int N;
    static int[] D;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1463.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        D = new int[1000001];
        D[1] = 0;
        D[2] = 1;
        D[3] = 1;

        for (int i = 4; i <= N; i++) {
            int min = D[i - 1];
            if (i % 2 == 0) min = Math.min(min, D[i / 2]);
            if (i % 3 == 0) min = Math.min(min, D[i / 3]);
            D[i] = min + 1;
        }

        System.out.println(D[N]);
    }
}
