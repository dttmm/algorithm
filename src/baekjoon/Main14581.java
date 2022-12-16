package baekjoon;

import java.io.*;

public class Main14581 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14581.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String fan = ":fan:";
        String s = br.readLine();

        bw.write(fan);
        bw.write(fan);
        bw.write(fan);
        bw.newLine();
        bw.write(fan);
        bw.write(":" + s + ":");
        bw.write(fan);
        bw.newLine();
        bw.write(fan);
        bw.write(fan);
        bw.write(fan);

        bw.flush();
        bw.close();
    }
}
