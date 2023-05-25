package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 13분 구현 8분
 최소가 되는 사이클을 어떻게 찾아야 할까

 최대 노드는 400이고 최대 간선은 16만인데
 각 점에서 다익스트라를 돌리게 되면
 400 * 16만 * log(16만) -> 시간 초과
 벨만 포드도 각 점에서 돌리게 되면 똑같이 시간 초과가 난다고라

 규칙이 있지는 않을까
 비용이 가장 적은 간선들 부터 고르면서 연결하면 되지 않을까 했는데
 비용이 똑같은 간선의 경우 처리가 안됨

 모든 노드에 대해서 각 노드로 가는 최소값을 구할 수는 없을끄아...?
 플로이드워셜이 뇌리에 따악
 원래 플로이드워셜은
 자기 자신으로 가는 비용을 0으로 했었는데
 이 비용을 무한으로 한다면??
 다른 곳으로 갔다가 다시 되돌아오는 경우도 고려해줄 수 있겠군하~
 */
public class Main1956 {

    static final int INF = 10000000;
    static int N;
    static int M;
    static int[][] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1956.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        d = new int[N + 1][N + 1];

        // 초기값 세팅
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                d[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            d[from][to] = cost;
        }

        // 플로이드 워셜
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    int newD = d[i][k] + d[k][j];
                    if (newD < d[i][j]) d[i][j] = newD;
                }
            }
        }

        // 자기 자신으로 가는 경로들 중 최소값 찾기
        int min = INF;
        for (int i = 1; i <= N; i++) min = Math.min(min, d[i][i]);

        // 예외 처리
        if (min == INF) System.out.println(-1);
        else System.out.println(min);
    }
}
