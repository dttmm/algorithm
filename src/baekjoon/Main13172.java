package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 문제 이해 실패
 다른 사람들의 해설을 봤는데도 아직까지 이해가 안됨
 사실 그냥 제곱하는 문제라는데 아직도 이해가 안됨..ㅠ

 n의 x제곱 구할 때
 x가 홀수 일 때
 return (half * half % X) * n % X 이렇게 하니까
 return solve(n, x-1) * n % X 일 때보다
 속도 10배 더 빨라짐
 */
public class Main13172 {

    final static int X = 1000000007;

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // n의 x제곱
    static long solve(int n, int x) {
        if (x == 1) return n;

        long half = solve(n, x / 2);

        if (x % 2 == 1) return (half * half % X) * n % X;
        else return half * half % X;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13172.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int M = Integer.parseInt(br.readLine());

        long answer = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int gcd = gcd(Math.max(n, s), Math.min(n, s));
            n /= gcd;
            s /= gcd;

            answer += s * solve(n, X - 2) % X;
            answer %= X;
        }

        System.out.println(answer);
    }
}
