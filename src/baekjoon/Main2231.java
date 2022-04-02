package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2231 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2231.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int min = 0;
        for (int i = N; i > 0; i--) {
            int n = i;
            int total = n;
            while (n != 0 || n % 10 != 0) {
                int rest = n % 10;
                total += rest;
                n /= 10;
            }
            if (total == N) {
                min = i;
            }
        }
        System.out.println(min);
    }
}
