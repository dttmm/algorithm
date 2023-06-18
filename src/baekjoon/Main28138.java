package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 설계 4분 구현 9분
 소수 문제
 N-R의 약수들 중에서
 N-R을 정확히 나눌 수 있는 약수들 중에서
 R보다 큰 약수들의 합을 구하면 됨
 */
public class Main28138 {

    static long N;
    static long R;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28138.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        R = Long.parseLong(st.nextToken());
        Set<Long> set = new TreeSet();

        // 약수 구하기
        long m = N - R;
        for (long x = 1; x <= Math.sqrt(m); x++) {
            if (m % x == 0) {
                set.add(-1 * x);
                set.add(-1 * (m / x));
            }
        }

        // 조건을 만족하는 약수 더하기
        long sum = 0;
        for (long x : set) {
            x *= -1;
            if (x > R) sum += x;
            else break;
        }

        System.out.println(sum);
    }
}
