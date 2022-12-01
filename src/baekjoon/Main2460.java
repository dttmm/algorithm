package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2460 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2460.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int total = 0;
        int max = 0;
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int leave = Integer.parseInt(st.nextToken());
            int ride = Integer.parseInt(st.nextToken());

            total -= leave;
            total += ride;
            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}
