package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1769 {
    static int count = 0;

    public static int solve(String s) {
        if (s.length() == 1) {
            return Integer.parseInt(s);
        } else {
            int x = 0;
            for (int i = 0; i < s.length(); i++) {
                x += s.charAt(i) - '0';
            }
            count++;
            return solve(String.valueOf(x));
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1769.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        long result = solve(s);

        System.out.println(count);
        if (result % 3 == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
