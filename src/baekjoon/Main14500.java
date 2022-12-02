package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 모든 가능한 도형의 모양으로 완탐을 했다
 처음에는 문제에서 주어진 5가지 모양으로 문제를 풀었다
 배열을 돌리면, 도형을 회전한거나 마찬가지이기 때문에
 ㅡ, h 모양의 경우 1번 회전하고
 ㄴ, ㅜ 모양의 경우 3번 회전했다
 하지만, 회전으로는 구할 수 없는 모양이 있었다
 h, ㄴ 모양의 경우 대칭을 시키면 완전 새로운 모양이 되기 때문에
 h, ㄴ 모양을 뒤집은 경우도 생각하여
 총 7가지의 모양을 완탐을 해서 풀었다

 각 도형의 모양을 하나의 델타 배열에 저장해놓고
 각 도형의 탐색 범위도 하나의 배열에 저장해놓고
 몇번 회전할지도 하나의 배열에 저장하여
 총 7번의 반복문으로 로직을 짰다
 */
public class Main14500 {

    // ㅁ ㅡ h ㄴ ㅜ h_대칭 ㄴ_대칭 순서의 델타값
    static int[][] di = {{0, 1, 0, 1}, {0, 0, 0, 0}, {0, 1, 1, 2}, {0, 1, 1, 1}, {0, 0, 0, 1}, {0, 1, 1, 2}, {1, 1, 1, 0}};
    static int[][] dj = {{0, 0, 1, 1}, {0, 1, 2, 3}, {0, 0, 1, 1}, {0, 0, 1, 2}, {0, 1, 2, 1}, {1, 0, 1, 0}, {0, 1, 2, 2}};

    // N M 제한 범위
    static int[] limit_N = {2, 1, 3, 2, 2, 3, 2};
    static int[] limit_M = {2, 4, 2, 3, 3, 2, 3};

    // 회전시킬 횟수
    static int[] rotate_num = {0, 1, 1, 3, 3, 1, 3};

    // 테트로미노가 올라간 불록들의 숫자 합 최대값 세는 함수
    static int solve(int[][] arr, int k, int N, int M, int limit_n, int limit_m) {
        int max = 0;
        for (int i = 0; i <= N - limit_n; i++) {
            for (int j = 0; j <= M - limit_m; j++) {
                int sum = 0;
                for (int dir = 0; dir < 4; dir++) {
                    int newI = i + di[k][dir];
                    int newJ = j + dj[k][dir];

                    sum += arr[newI][newJ];
                }
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    // 90도 회전시키는 함수
    static int[][] rotate(int[][] arr, int N, int M) {
        int[][] brr = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                brr[i][j] = arr[N - 1 - j][i];
            }
        }
        return brr;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14500.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        int max = 0;

        for (int k = 0; k < 7; k++) {
            int n = N;
            int m = M;
            int limit_n = limit_N[k];
            int limit_m = limit_M[k];
            int rotate = rotate_num[k];
            int[][] brr = arr;

            do {
                max = Math.max(max, solve(brr, k, n, m, limit_n, limit_m));

                brr = rotate(brr, n, m);
                int temp = n;
                n = m;
                m = temp;

                rotate--;
            } while (rotate >= 0);
        }

        System.out.println(max);
    }
}
