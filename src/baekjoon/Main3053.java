package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main3053 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3053.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double N = Double.parseDouble(br.readLine());
        System.out.printf("%.6f\n", Math.PI * N * N);
        System.out.printf("%.6f\n", (2 * N) * (2 * N) / 2);
    }
}
