package softeer.lv4;

import java.util.*;
import java.io.*;

/**
 전형적인 dp문제

 어후
 문제 이해하기 엄청 힘들었다
 입력 설명이 너무 이상?하다
 입력 두 번째 줄부터는 j+1작업장에 대해서
 각 라인의 작업장에서 걸리는 시간과
 각 라인에서 다른 라인으로 이동하는데 걸리는 시간이 같이 주어지는 군하
 이 부분에 대한 설명이 너무 빈약하다 이해하기 힘들었다

 쨌든
 답을 구하기 위해서는
 각 작업장을 탐색(j=1~<=N)하면서
 자기자신 라인 포함하여 다른 라인의 이전 작업장(j-1)에서 올 수 있는 경우 중
 이동시간 + 이전 작업장(j-1)에서의 작업시간이 최소인 것을 골라가며 저장하면 됨

 각 라인의 각 작업장에서 걸리는 작업 시간이 2차원 배열(arr)로 저장했는데
 다른 라인에서 다른 라인의 작업장으로 가는데 걸리는 이동시간을 어떻게 저장할지가 관건이었음
 3차원 배열(move)을 이용하여 K라인에서 K라인의 N작업장으로 가는데 걸리는 시간을 저장함
 입력값을 저장하는 부분이 좀 힘들었음
 */
public class Main_001_복잡한_조립라인1 {

    public class Main {

        static int K;   // 라인의 수
        static int N;   // 작업장의 수
        static int[][] arr;
        static int[][][] move;
        static int[][] d;

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            arr = new int[K + 1][N + 1];    // k라인의 N작업장에서 걸리는 시간
            move = new int[K + 1][K + 1][N + 1];   // K라인에서 K라인 N작업장까지 가는데 걸리는 이동시간
            d = new int[K + 1][N + 1];   // K라인 N작업장까지 최소 작업 시간 저장

            // 입력 받기
            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());

                // i라인의 j작업장에서 걸리는 시간 저장
                for (int i = 1; i <= K; i++) {
                    int n = Integer.parseInt(st.nextToken());
                    arr[i][j] = n;
                }

                // 마지막 작업장의 경우 다음 작업장(j+1)이 없으므로 패쓰
                if (j == N) break;

                // i라인에서 h라인의 (j+1)작업장까지 가는데 걸리는 이동시간
                for (int i = 1; i <= K; i++) {
                    for (int h = 1; h <= K; h++) {
                        if (i == h) continue;
                        move[i][h][j + 1] = Integer.parseInt(st.nextToken());
                    }
                }
            }

            // dp 구현
            // j작업장을 차례로 돌면서
            for (int j = 1; j <= N; j++) {

                // i라인의 j작업장에서의 최소 시간 저장
                for (int i = 1; i <= K; i++) {
                    int min = Integer.MAX_VALUE;

                    // h라인에서 i라인의 j작업장까지 오는 시간 + h라인 (j-1)작업장에서 걸리는 시간 중 최소값 찾기
                    for (int h = 1; h <= K; h++) {
                        min = Math.min(min, move[h][i][j] + d[h][j - 1]);
                    }
                    d[i][j] = min + arr[i][j];
                }
            }

            // 마지막 작업장에서 최소값 라인 찾기
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= K; i++) {
                min = Math.min(min, d[i][N]);
            }

            System.out.println(min);
        }
    }
}
