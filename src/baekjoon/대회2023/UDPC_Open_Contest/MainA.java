package baekjoon.대회2023.UDPC_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 계산 문제
 */
public class MainA {

    static int N;
    static int x;
    static int y;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/UDPC_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char c = s.charAt(0);

            if (c == 'D') {
                x++;
            } else {
                y++;
            }

            if (Math.abs(x - y) == 2) break;
        }

        System.out.println(x + ":" + y);
    }
}
