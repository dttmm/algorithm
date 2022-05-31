package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2747 {
    static int[] d;

    public static int f(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (d[n] != 0) return d[n];
        return d[n] = f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2747.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        d = new int[46];
        System.out.println(f(Integer.parseInt(br.readLine())));
    }
}
