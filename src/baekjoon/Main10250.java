package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10250 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10250.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int Y = N % H;
            int X = N / H + 1;
            if (N % H == 0) {
                Y = H;
                X = N / H;
            }
            if (X < 10) {
                System.out.println(Y + "0" + X);

            } else {
                System.out.println(Y + "" + X);
            }
        }
    }
}
