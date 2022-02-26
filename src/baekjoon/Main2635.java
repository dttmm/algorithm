package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2635 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2635.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int max = 0;
        String max_s = "";

        for (int i = 1; i <= N; i++) {
            int pre = N;
            int cur = i;
            int next = pre - cur;

            int count = 2;
            String s = "";
            s += pre + " " + cur + " ";

            while (next >= 0) {
                s += next + " ";
                pre = cur;
                cur = next;
                next = pre - cur;
                count++;
            }
            if (count > max) {
                max = count;
                max_s = s;
            }
        }
        System.out.println(max);
        System.out.println(max_s);
    }
}
