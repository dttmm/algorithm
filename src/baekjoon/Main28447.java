package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 2분 구현 7분 디버깅 12분
 조합
 N개중에 K개 고른다음
 고른 K개 중에서 두 개 쌍에 대한 궁합을 더함

 틀림
 아 최대값이 마이너스가 될 수 있기에
 max값 초기화를 0이 아닌 마이너스로 해줘야 됐네
 */

public class Main28447 {

    static int N;
    static int K;
    static int[][] c;   // 궁합
    static int max;
    static int[] tr;    // N개 중 K개 고름

    // N개 중 K개 고르기
    static void solve(int k, int start) {
        // K개 골랐을 경우
        if (k == K) {
            int total = 0;
            // 두 쌍에 대한 궁합의 총합 구함
            // 하나 선택하고
            for (int i = 0; i < K; i++) {
                // 나보다 큰 놈 하나 더 선택함
                for (int j = i + 1; j < K; j++) {
                    int a = tr[i];  // 첫번째 재료
                    int b = tr[j];  // 두번째 재료
                    total += c[a][b];
                }
            }
            max = Math.max(max, total);
        }
        // 조합으로 K개 고르기
        else {
            for (int i = start; i < N; i++) {
                tr[k] = i;
                solve(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28447.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = new int[N][N];
        max = Integer.MIN_VALUE;
        tr = new int[K];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                c[i][j] = n;
            }
        }

        // N개 중에서 K개 고르기
        solve(0, 0);

        System.out.println(max);
    }
}
