package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 처음에는 투 포인터로 접근했는데
 투포인터로 풀기 복잡한 없는 예외 발생해서
 40분 동안 헤멤

 그냥 문자열에서 중복 찾을 때 사용하는 것처럼
 이차원 dp이용해서 풀음
 */
public class Main_006_비밀_메뉴 {

    static int M;
    static int N;
    static int K;
    static int[] arr;
    static int[] secret;
    static int[][] d;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        secret = new int[M + 1];
        d = new int[M + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int n = Integer.parseInt(st.nextToken());
            secret[i] = n;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (secret[i] == arr[j]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                }
            }
        }

        // 마지막 줄에서 M개 만큼 연속으로 일치한 것이 있는지 확인
        String answer = "normal";
        for (int j = 1; j <= N; j++) {
            if (d[M][j] == M) {
                answer = "secret";
                break;
            }
        }

        System.out.println(answer);
    }
}
