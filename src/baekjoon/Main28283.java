package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 8분 구현 10분
 bfs
 처음에는 어떤 컴퓨터를 감염시켜야할까
 50만개중에 X개를 어떻게 고를까 생각했는데
 그냥 보안 시스템을 전파 시키면서
 각 컴퓨가 몇 분 동안 돈을 벌 수 있는지(visited)를 저장하고
 나중에 각 컴퓨터가 1분동안 벌 수 있는 돈과 몇 분동안 돈을 벌었는지를 계산하여
 최대로 돈을 많이 번 컴퓨터를 X개 선택함
 그리고 최소로 돈을 번 컴퓨터가 번 돈이 음수라면
 보안 시스템이 전파되지 않았다는 뜻이므로 <- 초기 visited값을 -1로 초기화함
 돈을 무한히 벌게 된다고 판단함
 */
public class Main28283 {

    static int N;
    static int M;
    static int X;
    static int Y;
    static long[] cost;             // 각 컴퓨터가 1분 동안 버는 돈
    static int[] visited;           // 방문처리 & 각 컴퓨터가 돈을 벌 수 있는 시간
    static List<Integer>[] lists;   // 연결 정보
    static Queue<Integer> queue;    // bfs용 큐

    // bfs
    static void solve() {
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : lists[v]) {
                // 이미 방문했으면 패쓰
                if (visited[u] > -1) continue;

                visited[u] = visited[v] + 1;
                queue.add(u);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28283.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        cost = new long[N + 1];
        visited = new int[N + 1];
        lists = new List[N + 1];
        queue = new LinkedList();

        // 초기화
        for (int i = 1; i <= N; i++) {
            visited[i] = -1;
            lists[i] = new ArrayList();
        }

        // 1분에 버는 돈 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            cost[i] = n;
        }

        // 연결 관계 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lists[a].add(b);
            lists[b].add(a);
        }

        // 보안 시스템 설치 컴퓨터 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Y; i++) {
            int n = Integer.parseInt(st.nextToken());
            queue.add(n);
            visited[n] = 0;
        }

        // bfs
        solve();

        // 각 컴퓨터가 총 벌수 있는 돈 구하기
        for (int i = 1; i <= N; i++) {
            cost[i] = cost[i] * visited[i];
        }

        // 정렬
        Arrays.sort(cost);

        long answer = 0;
        // 돈을 무한히 벌 수 있는 경우
        if (cost[0] < 0) answer = -1;
        else {
            // 돈을 제일 많이 버는 컴퓨터 X개 선택
            for (int i = 0; i < X; i++) {
                answer += cost[cost.length - 1 - i];
            }
        }

        System.out.println(answer);
    }
}
