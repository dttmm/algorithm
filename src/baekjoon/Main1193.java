package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1193 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1193.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int max = 2;
        int cur = 1;
        int start = 1;
        for (int i = 0; i < N; i++) {
            if (start == max) {
                max++;
                start = 1;
            }
            cur = start++ % max;
        }
        if (max % 2 == 0) {
            cur = max - cur;
        }
        System.out.println(cur + "/" + (max - cur));
    }
}
