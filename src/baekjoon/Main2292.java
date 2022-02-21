package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2292 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2292.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int i;
        N -= 1;
        for (i = 1; N > 0; i++) {
            N -= 6 * i;
        }
        System.out.println(i);
    }
}
