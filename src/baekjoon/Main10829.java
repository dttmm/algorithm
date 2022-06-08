package baekjoon;

import java.io.*;

public class Main10829 {
    static long N;
    static String s;

    public static void f(long n) {
        if (n == 0) return;

        s += n % 2;
        f(n / 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10829.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = "";
        N = Long.parseLong(br.readLine());

        f(N);

        String newS = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            newS += s.charAt(i);
        }
        System.out.println(newS);
    }
}
