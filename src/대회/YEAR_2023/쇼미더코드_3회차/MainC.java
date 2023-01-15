package 대회.YEAR_2023.쇼미더코드_3회차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 조합으로 접근을 했었음
 인원수가 많은 쪽에서 인원수가 적은 쪽 만큼 뽑아서
 최대값을 구했는데
 인원이 적은 쪽에서 꼭 모든 사람이 악수를 할 필요가 없는 경우가 발생
 그래서 조합에 백트래킹을 추가해서 악수를 할 때마다 최대값을 구해주었음
 근데 시간초과 발생
 1000명과 500명이 악수를 해야되는 경우
 경우의 수는 계산불가 할 정도로 발생

 쵀대값을 구하기 위해서는 모든 경우를 다 따져봐야 되는데
 모든 경우를 따졌더니 시간초과?
 이건 무조건 dp라고 생각해서
 dp적 사고를 하였음

 하지만 dp가 떠오르지 않아서 우선순위로 해야되나 그리디로 해야되나 하고 고민함
 하지만 dp가 갑자기 떠오름

 이차원 dp를 이용하여
 i번째 사람이 j번째 사람과 악수를 하게 될 경우
 1) i-1번째 사람과 j-1번째 사람까지의 최대값 + i번째 사람이 j번째 사람이 악수를 하게 될 경우의 만족도
 2) i번째 사람과 j-1번째 사람까지의 최대값
 3) i-1번째 사람과 j번째 사람까지의 최대값
 위 3가지 경우중 최대값이 바로 i번째 사람이 j번째 사람까지의 최대값이 된 다는 것을 발견하게 됨
 */
public class MainC {

    static int N;
    static int M;
    static int C;
    static int[][] w;
    static int[] arr;
    static int[] brr;
    static long[][] d;


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/대회/YEAR_2023/쇼미더코드_3회차/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        w = new int[C + 1][C + 1];
        d = new long[N + 1][M + 1];

        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                int n = Integer.parseInt(st.nextToken());
                w[i][j] = n;
            }
        }

        arr = new int[N];
        brr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());
            brr[i] = n;
        }

        for (int i = 1; i <= N; i++) {
            int n = arr[i - 1];
            for (int j = 1; j <= M; j++) {
                int m = brr[j - 1];

                d[i][j] = Math.max(Math.max(d[i - 1][j], d[i - 1][j - 1] + w[n][m]), d[i][j - 1]);
            }
        }

        System.out.println(d[N][M]);
    }
}
