package baekjoon;

import java.io.*;

public class Main11729 {
    static int N;
    static BufferedWriter bw;

    public static void f(int n, int s, int e) throws IOException {
        if (n == 0) return;
        if (n == 1) {
            bw.write(s + " " + e);
            bw.newLine();
            return;
        }

        f(n - 1, s, (6 - s - e));
        bw.write(s + " " + e);
        bw.newLine();
        f(n - 1, (6 - s - e), e);

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11729.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        System.out.println((int) Math.pow(2, N) - 1);
        f(N, 1, 3);
        bw.flush();
        bw.close();
    }
}
