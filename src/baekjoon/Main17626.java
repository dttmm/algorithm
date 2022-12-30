package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 문제를 보자마자 dp인 느낌을 받긴 받았는데
 규칙을 찾기가 어려웠다

 예를들어 N이 10 이라면
 N(10)보다 작은 제곱수들 중에서 가장 큰수(9)를 찾은 다음에
 d[9] + d[N-9]를 하면 될줄 알았다
 근데 18의 경우 위 방법으로 구하면 d[16]+d[2]로
 16+1+1이지만
 9+9가 되는 경우가 따로 있다

 해당 예외를 찾기 위해서는
 그냥 N보다 작은 제곱수들을 가지고
 d[제곱수] + d[N-제곱수]중에서 가장 작은 녀석을 뽑으면 되지만
 시간제한이 0.5초에 N이 50000일 경우
 50000보다 작은 제곱수가 200개가 넘어서
 모든 제곱수들을 탐색하는 것은 시간안에 안될거라고 판단

 도저히 다른 방법이 안떠올라서 답을 봤는데
 시간안에 되네?
 50000*200해도 0.1초밖에 안걸리는구나..
 */

public class Main17626 {

    static int[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17626.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        d = new int[N + 1];

        int n = 1;
        int x = 1;
        while (x <= N) {
            d[x] = 1;
            n++;
            x = n * n;
        }

        for (int i = 1; i <= N; i++) {
            int sqrt = (int) Math.sqrt(i);

            int min = 4;
            for (int j = 1; j <= sqrt; j++) {
                int nn = j * j;
                min = Math.min(min, d[nn] + d[i - nn]);
            }
            d[i] = min;
        }


        System.out.println(d[N]);
    }
}
