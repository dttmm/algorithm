package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 전형적인 분할정복 문제
 그냥 바로 뚝딱뚝딱함
 */
public class Main_009_바이러스 {

    final static int R = 1000000007;
    static int K;
    static int P;
    static int N;

    static long solve(int n) {
        if (n == 1) return P;

        long half = solve(n / 2);

        // 홀수일 때
        if (n % 2 == 1) {
            return (((half * half) % R) * P) % R;
        } else {
            return (half * half) % R;
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        long answer = (K * solve(N)) % R;

        System.out.println(answer);
    }
}
