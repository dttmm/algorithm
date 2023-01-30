package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 최대공약수 구하는 문제
 처음에는 dp인줄 알았다가
 dp로 접근하려고 보니까 숫자 범위가 너무 크길래 흠칫함
 */
public class Main25333 {

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25333.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int gcd = gcd(Math.max(a, b), Math.min(a, b));
            bw.write(x / gcd + "\n");
        }
        bw.flush();
        bw.close();
    }
}
