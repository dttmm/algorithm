package softeer.lv5;

import java.util.*;
import java.io.*;

/**
 처음에는 간단할 줄 알았으나..
 생각보다 생각할 것이 많아군하

 복잡한 조립라인1 문제와 마찬가지로 dp로 풀려고 했는데
 처음에는 라인 통틀어서 N작업장에서 걸리는 최소 작업시간을 고르고
 N작업장에서 최소 작업 시간이 걸리는 라인들과
 N-1작업장에서 최소 작업 시간이 걸리는 라인들중에 중복된 값이 있으면
 중복된 라인을 선택하면 이동시간이 없으니까
 이전에 더했던 이동시간을 total에서 빼줌
 근데, 최소 작업 시간이 걸리는 라인을 선택하다보면 이전에 선택한 라인이 최선이 라인이 아니게 되는 상황이 발생하게 됨
 ex) 10                 1
        -> 이동시간 10
     9                  100
 각 작업장의 최소값을 구하면 9,1에 이동시간 10을 더하면 20인데
 작업장을 10,1 선택하면 11로 최소값이 되어버림
 아.. 그냥 모든 라인들에 대해서 dp를 구해야 되는 구낭..

 작업장을 순차 탐색하면서
 모든 라인에 대해 dp를 구하였음
 만약 어떤 라인(i)이 이전 작업장에서 최소 작업 시간이 걸리는 라인들 모음에 속해있다면
 다른 라인에서 오는 경우를 비교할 필요 없이 바로 그것이 해당 작업장의 해당 라인까지 오는데 최소값이 됨
 만약 어떤 라인(i)이 이전 작업장에서 최소 작업 시간이 걸리는 라인들 모음에 속해있지 않다면
 다른 라인에서 오는 경우를 비교해야됨
 */
public class Main_001_복잡한_조립라인2 {

    public class Main {

        static int N;   // 작업장
        static int K;   // 라인수
        static HashSet<Integer>[] min_idx_sets;
        static int[] mins;
        static int[] moveTimes;
        static int[][] arr;
        static int[][] d;

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            min_idx_sets = new HashSet[N + 1];   // N작업장에서 최소 작업 시간이 걸리는 라인들 모음
            mins = new int[N + 1]; // 라인 통틀어서 N작업장에서 걸리는 최소 작업시간
            moveTimes = new int[N]; // N작업장에서 N+1작업장으로 가는데 걸리는 이동시간
            arr = new int[K + 1][N + 1];    // K라인의 N작업장에서의 작업 시간
            d = new int[K + 1][N + 1];  // dp

            for (int j = 0; j <= N; j++) {
                min_idx_sets[j] = new HashSet();
            }

            for (int j = 1; j <= N; j++) {
                st = new StringTokenizer(br.readLine());

                // K라인의 N작업장에서의 작업 시간 저장해줌
                for (int i = 1; i <= K; i++) {
                    int n = Integer.parseInt(st.nextToken());
                    arr[i][j] = n;
                }

                // N작업장이 마지막 작업장이므로 N+1작업장으로 가는 이동시간은 없으므로 패쓰
                if (j == N) continue;

                // j작업장에서 j+1작업장으로 가는데 걸리는 시간
                int move_time = Integer.parseInt(st.nextToken());
                moveTimes[j] = move_time;
            }

            for (int j = 1; j <= N; j++) {

                int min = Integer.MAX_VALUE;    // 현재 작업장에서 최소 작업 시간

                for (int i = 1; i <= K; i++) {

                    // 이전 작업장(j-1)에서 최소 작업 시간이 걸리는 라인들중에 현재 라인이 있으면
                    // 이동 시간은 고려하지 않아도 되므로 이전 작업장의 최소 작업 시간에 현재 라인의 작업장 작업 시간 더해주면 됨
                    if (min_idx_sets[j - 1].contains(i)) {
                        d[i][j] = d[i][j - 1] + arr[i][j];
                    }
                    // 이전 작업장(j-1)에서 최소 작업 시간이 걸리는 라인들중에 현재 라인이 없으면
                    // 현재 라인의 이전 작업장에서 왔을 경우와
                    // 최소 작업 시간이 걸리는 라인에서 왔을 경우 중 최소값을 선택
                    else {
                        d[i][j] = Math.min(d[i][j - 1], mins[j - 1] + moveTimes[j - 1]) + arr[i][j];
                    }

                    // 해당 작업장에서 최소 작업시간과 최소 작업 시간이 걸리는 라인들을 저장해줌
                    if (d[i][j] < min) {
                        min_idx_sets[j].clear();

                        min = d[i][j];
                        min_idx_sets[j].add(i);
                    } else if (d[i][j] == min) {
                        min_idx_sets[j].add(i);
                    }
                }
                mins[j] = min;
            }

            // 마지막 작업장 중에서 최소값 찾아내기
            int answer = Integer.MAX_VALUE;
            for (int i = 1; i <= K; i++) {
                answer = Math.min(answer, d[i][N]);
            }

            System.out.println(answer);
        }
    }
}
