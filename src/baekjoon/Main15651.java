package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main15651 {
    static int N;
    static int M;
    static int[] tr;
    static String s;
    static BufferedWriter bw;

    public static void p(int k) throws Exception {

        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tr[i] + " ");
            }
            bw.newLine();
        } else {
            for (int i = 1; i <= N; i++) {
                tr[k] = i;
                p(k + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15651.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tr = new int[M];

        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        s = "";
        p(0);

        bw.flush();
        bw.close();
    }
}
