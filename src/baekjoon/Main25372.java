package baekjoon;

import java.io.*;

public class Main25372 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25372.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int len = br.readLine().length();
            if (len >= 6 && len <= 9) bw.write("yes\n");
            else bw.write("no\n");
        }
        bw.flush();
        bw.close();
    }
}
