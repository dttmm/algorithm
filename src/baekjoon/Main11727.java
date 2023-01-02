package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 규칙이 잘 보이지 않아서
 2×n 타일링 문제를 참고함
 2×n 타일링 문제에서는 n-1에서 2*1타일 추가하고
 n-2에서 (1*2)*2타일 추가해서 점화식이 d[i - 1] + d[i - 2]으로 나옴

 이 문제에서는
 n-1에서 2*1타일 추가하고
 n-2에서 (1*2)*2타일과 2*2타일 추가해서
 점화식이 d[i - 1] + 2 * d[i - 2]으로 나옴
 */
public class Main11727 {

    static int N;
    static int[] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11727.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        d = new int[1001];

        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i <= N; i++) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 10007;
        }

        System.out.println(d[N]);
    }
}
