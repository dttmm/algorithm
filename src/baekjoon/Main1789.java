package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1789 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1789.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        long sum = 0;
        int i = 1;
        while (true) {
            sum += i++;
            if (sum > N) break;
        }

        System.out.println(i - 2);
    }
}
