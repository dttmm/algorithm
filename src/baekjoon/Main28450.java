package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 6분 구현 19분 디버깅 11분
 bfs
 [0,0]에서부터 시작해서 bfs돌리면서
 현재 남은 은식력을 visited배열에 저장해감
 방문한 적이 없거나 이미 방문했는데 남은 은신력이 더 높은 경우 큐에 추가해줬음

 시간초과
 한번 방문했을 때 큐에 넣고
 후에 은신력이 더 높은 경우에 똑같은 자리에 대해 큐에 값이 중복으로 들어가게되면서
 시간 초과 발생
 이건 bfs로 풀면 안되고 dp로 풀어야 겠다고 판단

 dp
 배열을 순차탐색 하면서
 d배열에 현재까지 눈치력 총합을 저장함
 위쪽과 왼쪽에서 오는 경우에 대해 눈치력이 더 낮은 것을 선택하여
 최종적으로 바벨까지 가는데 총 눈치력(d[N-1][M-1]) 출력
 d배열을 INF로 초기화 해주었기 때문에
 바벨까지 가는데 총 눈치력이 INF라면 도달 못한 것이므로 실패 출력

 근데 bfs로도 풀 수 있구나
 큐에서 값을 꺼낼 때
 꺼낸 값에서 남아있는 은신력이 visited에 저장되어 있는 값보다 작으면
 그냥 continue시켜서 무시하면 되넹
 그래서 bfs로도 다시 풀어봄

 dp 사용 했을 때: 500ms
 bfs 사용 했을 때: 625ms
 */
public class Main28450 {

    static int INF = 100000001;
    static int N;
    static int M;
    static int H;
    static int[][] arr;
    static int[][] d;   // 현재까지 눈치력 총합의 최소

    // dp
    static void solve() {
        d[0][0] = arr[0][0];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = arr[i][j];

                // 위쪽과 현재 위치에서의 눈치력 비교
                if (i > 0) d[i][j] = Math.min(d[i][j], d[i - 1][j] + n);
                // 왼쪽과 현재 위치에서의 눈치력 비교
                if (j > 0) d[i][j] = Math.min(d[i][j], d[i][j - 1] + n);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28450.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        d = new int[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;

                // 초기화
                d[i][j] = INF;
            }
        }

        H = Integer.parseInt(br.readLine());

        // dp
        solve();

        // 바벨에 도달 못할 경우
        if (d[N - 1][M - 1] > H) System.out.println("NO");
        else System.out.println("YES\n" + d[N - 1][M - 1]);
    }
}
