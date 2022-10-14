package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main5525 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5525.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int lengthP = 2 * N + 1;
        String target = "";
        for (int i = 0; i < lengthP; i++) {
            if (i % 2 == 0) target += "I";
            else target += "O";
        }

        int sum = 0;
        for (int i = 0; i < M - lengthP + 1; i++) {
            String part = s.substring(i, i + lengthP);
            if (target.equals(part)) sum++;
        }

        System.out.println(sum);
    }
}
