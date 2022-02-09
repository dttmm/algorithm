package baekjoon;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main8958 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/8958.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            int sum = 0;
            int count = 0;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == 'O') {
                    count++;
                } else {
                    count = 0;
                }
                sum += count;
            }
            System.out.println(sum);
        }
    }
}
