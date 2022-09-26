package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main10162 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10162.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int A = 0;
        int B = 0;
        int C = 0;

        if (N % 10 > 0) {
            System.out.println(-1);
            return;
        }

        int n = N / 300;
        if (n > 0) {
            A = n;
            N = N % 300;
        }

        n = N / 60;
        if (n > 0) {
            B = n;
            N = N % 60;
        }

        C = N / 10;
        System.out.println(A + " " + B + " " + C);
    }
}
