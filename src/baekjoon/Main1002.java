package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1002 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1002.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            int diff = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
            int diff_r = (r1 - r2) * (r1 - r2);
            int r = (r1 + r2) * (r1 + r2);
            if (diff < r) {
                if (diff < diff_r) {
                    System.out.println(0);
                } else if (diff > diff_r) {
                    System.out.println(2);
                } else {
                    if (x1 == x2 && y1 == y2) {
                        System.out.println(-1);
                    } else {
                        System.out.println(1);
                    }
                }
            } else if (diff > r) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
    }
}
