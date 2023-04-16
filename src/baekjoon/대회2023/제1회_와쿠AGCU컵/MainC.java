package baekjoon.대회2023.제1회_와쿠AGCU컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 N보다 크거나 같으면서 제일 작은 2의 제곱수를 찾는 문제
 분할정복 함수 하나 만들고
 깊이를 타고 들어갈 때마다 횟수를 1씩 증가시켜서 리턴함

 4의 경우 2^2보다 작거나 같고
 5의 경우 2^3보다 작음
 둘다 2로 계속 나눌 경우 둘다 2번만에 1로 만들수 있는데
 제일 작은 2의 제곱수를 어떻게 구별할 수 있을까 생각함

 N*2-1을 해주면 제곱수를 구분 가능하다는 것을 발견
 4를 두배 해주고 1을 빼준 7을 계속 2로 나누면 3번만에 1로 만들 수 있고
 5를 두배 해주고 1을 빼준 9를 계속 2로 나누면 4번만에 1로 만들 수 있음
 */
public class MainC {

    static long N;

    // 분할 정복
    static int solve(long n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        return 1 + solve(n / 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_와쿠AGCU컵/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        System.out.println(solve(2 * N - 1));
    }
}
