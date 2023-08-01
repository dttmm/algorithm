package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 구현 17분 디버깅 7분
 외판원 순회 공부하고 풀어봄
 아이디어 생각하는게 진짜 어렵다
 dp와 비트마스크 힌트 보고
 d[i][j]를 현재 i노드이고 현재 지나온 노드가 j일때 현재 상태까지의 최소값이라 두고 풀었는데
 이게 아니라 현재 상태에서 남은 노드를 다 방문했을 때의 최소값이라 생각하고 풀어야 하는 군하

 틀림
 d배열을 초기화할 때 INF로 초기화 했는데
 이렇게하면 d배열에 저장되어 있는 INF가
 방문을 안한 것인지 더이상 갈 수가 없는 것인지 판별할 수가 없어서
 무한 재귀를 돌게 된다는 것을 발견!!
 */
public class Main2098 {

    static final int INF = 100000000;
    static int N;
    static int standard;    // 시작 노드
    static int[][] arr;     // 연결 관계
    static int[][] d;       // 현재 i노드이고 현재 j노드들을 지나왔을 때, 앞으로 남은 노드를 방문하기 위한 비용의 최소값

    // 외판원 순회
    static int solve(int v, int visited) {
        // 모든 노드를 방문한 경우
        if (visited == (1 << N) - 1) {
            return arr[v][standard];    // 현재 노드에서 시작 노드로 가는 비용
        }

        // 이미 해당 경우를 계산한 경우
        if (d[v][visited] != -1) return d[v][visited];
        d[v][visited] = INF;    // 현재 상태에서 최소값 구하기 위해 초기화

        // 이웃노드 탐색
        for (int u = 0; u < N; u++) {
            // 갈 수 없는 경우
            if (arr[v][u] == INF) continue;
            // 이미 해당 노드를 방문한 경우
            if ((visited & 1 << u) > 0) continue;

            // 최소값 갱신
            d[v][visited] = Math.min(d[v][visited], solve(u, visited | (1 << u)) + arr[v][u]);
        }

        return d[v][visited];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2098.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        d = new int[N][1 << N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0) n = INF;    // 갈 수 없는 경우
                arr[i][j] = n;
            }
        }

        // 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 1 << N; j++) {
                d[i][j] = -1;
            }
        }
        standard = 0;   // 시작 지점 설정

        // 시작 지점에서 순회 시작
        int result = solve(standard, 1 << standard);

        System.out.println(result);
    }
}
