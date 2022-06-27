package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main4779 {
    static int N;
    static Map<Integer, String> map;
    static String[] S;

    public static String solve(String s) {
        int length = s.length();

        if (length == 1) {
            return s;
        } else {
            String s1 = s.substring(0, length / 3);
            s = solve(s1) + s1.replace("-", " ") + solve(s1);
        }
        return s;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4779.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new HashMap();
        S = new String[13];
        S[0] = "-";

        for (int i = 1; i <= 12; i++) {
            S[i] = S[i - 1] + S[i - 1] + S[i - 1];
        }

        while (br.ready()) {
            N = Integer.parseInt(br.readLine());

            System.out.println(solve(S[N]));
        }
    }
}
