package baekjoon;

import java.io.*;

public class Main1259 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1259.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String s = br.readLine();
            if (s.equals("0")) break;

            boolean flag = true;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != s.charAt(s.length() - (i + 1))) {
                    flag = false;
                    break;
                }
            }
            if (flag) bw.write("yes");
            else bw.write("no");
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
