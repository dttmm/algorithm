package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main11653 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11653.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            return;
        }

        int i = 2;
        int j = 2;
        while (N > 1) {
            boolean flag = true;
            for (; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag && N % j == 0) {
                System.out.println(j);
                N /= j;
                i = 2;
            } else if (flag && N % j != 0) {
                j++;
            } else {
                i++;
            }
        }
    }
}
