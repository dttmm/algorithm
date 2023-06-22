package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 22분 구현 36분
 누가봐도 dp문제
 투자액을 컬럼으로 잡고
 기업을 로우로 잡아서
 한 기업을 포함하였을 때, 해당 투자 금액에서의 최대값을 d에 저장함
 최대값의 경우 새로 포함한 기업에 0~N원까지 투자하였을 때 +
 해당 기업을 제외하고 N~0원까지 투자하였을 때의 최대값을 구했음

 최대값은 dp로 구했는데
 문제는 최대값을 구하기 위한 각 기업의 투자액을 구하는 것이 문제
 최대값을 구할 때마다 해당 구역에서 사용된 기업의 투자액을 p에 저장함
 그리고 p의 끝에서부터 거슬러 올라 가면서
 각 기업의 투자액을 path에 저장하고 출력함
 */
public class Main2662 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] d;
    static int[][] p;
    static int[] path;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2662.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        d = new int[M + 1][N + 1];
        p = new int[M + 1][N + 1];
        path = new int[M + 1];

        // 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) {
                int cost = Integer.parseInt(st.nextToken());
                arr[i][j] = cost;
            }
        }

        // 이차원 dp
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                int max = 0;
                for (int k = 0; k <= j; k++) {
                    if (arr[k][i] + d[i - 1][j - k] > max) {
                        max = arr[k][i] + d[i - 1][j - k];
                        p[i][j] = k;
                    }
                }
                d[i][j] = max;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(d[M][N] + "\n");

        // 최대값 경로 구하기
        int i = M;
        int j = N;
        while (j != 0) {
            int cost = p[i][j];
            path[i] = cost;
            j -= cost;
            i -= 1;
        }

        for (int k = 1; k <= M; k++) {
            sb.append(path[k] + " ");
        }

        System.out.println(sb);
    }
}
