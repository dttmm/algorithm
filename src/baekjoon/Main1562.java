package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 넘모 어렵다
 처음에는
 각 자릿수 i에서 숫자 j로 시작하는 계단 수를
 이차원 dp로 관리했지만
 0부터 9까지 어떤 수를 갖고 있는지에 대한 정보를 따로 관리할 필요가 있었음
 그래서 3차원 dp로
 자릿수 i이면서 j로 시작하면서 0~9까지 어떤 수를 들고 있는지를 관리하려고 했지만
 아이디어 못 떠올려서 실패

 결국 도움!
 아
 3차원 dp 마지막 열의 크기를 10으로 잡아서 관리하려고 했었는데
 비트마스킹을 이용하여 1<<10으로 관리해야 되는 군하
 아이디어를 얻었음에도 불구하고
 구현하는데 비트마스킹이 헷갈려서 고생함
 어렵다
 */
public class Main1562 {

    static final long MOD = 1000000000L;
    static int N;
    static long[][][] d;    // i자릿수 j로 시작하면서 0~9까지를 얼마나 들고 있는지 h

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1562.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        d = new long[N + 1][10][1 << 10];

        // 초기화
        for (int i = 0; i < 10; i++) {
            d[1][i][1 << i] = 1;
        }

        // dp + 비트마스킹
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int h = 0; h < 1 << 10; h++) {
                    if (j != 0)
                        d[i][j][h | (1 << j)] = (d[i][j][h | (1 << j)] + d[i - 1][j - 1][h]) % MOD;
                    if (j != 9)
                        d[i][j][h | (1 << j)] = (d[i][j][h | (1 << j)] + d[i - 1][j + 1][h]) % MOD;
                }
            }
        }

        // N자리 계단 수 구하기
        long answer = 0;
        for (int i = 1; i < 10; i++) {
            answer = (answer + d[N][i][1023]) % MOD;
        }
        System.out.println(answer);
    }
}
