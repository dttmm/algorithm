package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11050 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11050.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int total = 1;
        for (int i = N; i > N - K; i--) {
            total *= i;
        }

        for (int i = K; i > 0; i--) {
            total /= i;
        }

        System.out.println(total);
    }
}
