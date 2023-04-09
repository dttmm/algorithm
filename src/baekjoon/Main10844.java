package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 i자리 수의 j로 시작하는 수의 정보를 이차원 dp에 저장
 만약 1xx로 시작하는 수의 경우
 0x의 계단 수 개수 + 2x의 계산 수 개수를 더해주면 됨
 단, 0이나 9로 시작하는 경우
 0xx의 경우 1x의 계단 수의 개수 만큼 계단 수를 가질 수 있고
 9xx의 경우 8x의 계단 수의 개수 만큼 계단 수를 가질 수 있음
 이 부분 분기처리 잘해주면 됨

 d배열의 크기를 11로 만들어서
 마지막에는 1~9로 시작하는 수의 계단 수 합을 저장해줌

 원래는 계단 수 풀려고 했는데
 아이디어 안떠올라서 쉬운 계단 수로 갈아탐..
 */
public class Main10844 {

    final static int R = 1000000000;
    static int N;
    static int[][] d;   // i자리 수의 j로 시작하는 수의 계단 수 개수

    static int solve() {

        // 1자리 수 정보 초기화
        for (int j = 0; j <= 9; j++) {
            d[1][j] = 1;
        }
        d[1][10] = 9;

        for (int i = 2; i <= N; i++) {
            long sum = 0;
            for (int j = 0; j <= 9; j++) {

                // 1로 시작하는 경우
                if (j == 0) {
                    d[i][j] = d[i - 1][j + 1];
                    continue;
                }

                // 9로 시작하는 경우
                else if (j == 9) d[i][j] = d[i - 1][j - 1];

                // 2~8로 시작하는 경우
                else d[i][j] = (d[i - 1][j - 1] + d[i - 1][j + 1]) % R;

                // 1~9로 시작하는 계단 수의 개수를 더해줌 (0으로 시작하는 수는 계단수가 아님)
                sum = (sum + d[i][j]) % R;
            }

            d[i][10] = (int) sum;
        }

        return d[N][10];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10844.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        d = new int[N + 1][11];

        System.out.println(solve());
    }
}
