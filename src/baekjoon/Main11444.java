package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 숫자가 말도안되게 크길래
 일단 dp를 이용하여 배열을 할당하기에는 불가은하다고 판단
 이분탐색이나 분할정복을 이용해야 되는데
 분할정복 규칙을 찾기위해 삽질하였으나 실패

 결국 풀이를 봄
 피보나치 행렬 점화식을 알아야되네
 그냥 아이디어로는 풀 수가 없는 문제였넹...
 */
public class Main11444 {

    final static long R = 1000000007L;
    static long N;
    static long[][] standard = {
            {1, 1},
            {1, 0}
    };

    static long[][] multiply(long[][] arr, long[][] brr) {
        long[][] crr = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    crr[i][j] += (arr[i][k] * brr[k][j]) % R;
                    crr[i][j] %= R;
                }
            }
        }
        return crr;
    }

    static long[][] solve(long n) {
        if (n <= 1) return standard;

        long[][] crr = solve(n / 2);

        if (n % 2 == 0) {
            return multiply(crr, crr);
        } else {
            return multiply(multiply(crr, crr), standard);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11444.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        long[][] result = solve(N - 1);
        System.out.println(result[0][0]);
    }
}
