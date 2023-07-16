package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 3븐 구현 3분
 귀여운 dp문제
 바텀업으로 구현해봄
 어떤 지점에 오기까지 최대값은
 이전에 올 수 있는 지점들 중에서
 최대값을 계속 더해나가면 됨
 */
public class Main11048 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11048.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M + 1];
        d = new int[N + 1][M + 1];

        // 입력 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        // 각 지점에서 최대값 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 위쪽, 왼쪽, 위왼쪽 방향에서 최대값 가져옴
                d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                d[i][j] = Math.max(d[i][j], d[i - 1][j - 1]);
                d[i][j] += arr[i][j];
            }
        }

        System.out.println(d[N][M]);
    }
}
