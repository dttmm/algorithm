package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main25400 {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25400.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int target = 1;
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == target) {
                target++;
            }
        }

        System.out.println(N - target + 1);
    }
}
