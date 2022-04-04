package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1436 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1436.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        int start = 666;
        while (N > 0) {
            int n = start;
            int flag = 0;
            while (n / 10 != 0 || n % 10 != 0) {
                if (n % 10 == 6) {
                    flag++;
                    if (flag == 3) break;
                } else flag = 0;
                n /= 10;
            }
            if (flag == 3) {
                N--;
                answer = start;
            }
            start++;
        }
        System.out.println(answer);
    }
}
