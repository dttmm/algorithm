package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 2시간
 처음에는 간단하게 bfs돌리면 될 것 같았음
 visited배열에 패턴 이동 횟수를 추가하여 3차원 배열로 최단 거리를 저장했는데
 중간 거점을 지났는지 여부도 같이 저장을 해야되더람
 그래서 차원 하나 더 추가해서 4차원 배열을 이용하여
 패턴 이동횟수와 중간 거점을 지났는지 여부도 같이 고려하여 최단거리 구해줌

 벽 부수고 이동하기에서 아이디어를 얻었는데
 다음에 방문할 지역이 이미 방문한 경우 처리를
 (visited[node.k][newI][newJ][node.isPassedMid] != INF) 했는데

 이미 방문 했더라도 최단거리를 또 발견할 수도 있으니까
 방문처리 겸 최단 거리 갱신을
 (node.depth >= visited[node.k][newI][newJ][node.isPassedMid])로 했는데
 메모리 초과가 나더라
 아 찐득하게 생각해보니까
 이미 패턴 이동을 써서 앞으로 빨리 간 경우
 다시 패턴 이동으로 뒤로 가게되면
 거의 무한 반복이 일어날 수 있겠군하
 */
public class Main25969 {

    static final int INF = 100000;
    static int N;
    static int M;
    static int K;
    static int[][] arr;
    static boolean[][] pattern;
    static int[][][][] visited; // [남은 패턴 사용 횟수, i, j, 중간 거점 지났는지]
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node {
        int i;
        int j;
        int k;              // 남은 패턴 사용 횟수
        int isPassedMid;    // 중간 거점 지났는지
        int depth;          // 거리

        public Node(int i, int j, int k, int isP, int depth) {
            this.i = i;
            this.j = j;
            this.k = k;
            this.isPassedMid = isP;
            this.depth = depth;
        }
    }

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static int bfs() {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(0, 0, K, 0, 0));
        visited[K][0][0][0] = 0;
        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 중간지점 거치고 목적지 도착 했을 때
            if (node.i == N - 1 && node.j == M - 1 && node.isPassedMid == 1) min = Math.min(min, node.depth);

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[node.k][newI][newJ][node.isPassedMid] != INF) continue;
                // 벽이면 패쓰
                if (arr[newI][newJ] == 1) continue;


                Node newNode;
                // 중간 지점인 경우
                if (arr[newI][newJ] == 2) {
                    newNode = new Node(newI, newJ, node.k, 1, node.depth + 1);
                } else {
                    newNode = new Node(newI, newJ, node.k, node.isPassedMid, node.depth + 1);
                }
                visited[node.k][newI][newJ][newNode.isPassedMid] = visited[node.k][node.i][node.j][node.isPassedMid] + 1;
                queue.add(newNode);
            }

            // 패턴 사용 횟수가 없으면 패쓰
            if (node.k == 0) continue;

            // 패턴으로 이동 가능한 곳 탐색
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!pattern[i][j]) continue;
                    int newI = node.i + (i - 2);
                    int newJ = node.j + (j - 2);

                    // 범위 벗어나면 패쓰
                    if (!isIn(newI, newJ)) continue;
                    // 이미 방문했으면 패쓰
                    if (visited[node.k - 1][newI][newJ][node.isPassedMid] != INF) continue;
                    // 벽이면 패쓰
                    if (arr[newI][newJ] == 1) continue;

                    Node newNode;
                    // 중간 지점인 경우
                    if (arr[newI][newJ] == 2) {
                        newNode = new Node(newI, newJ, node.k - 1, 1, node.depth + 1);
                    } else {
                        newNode = new Node(newI, newJ, node.k - 1, node.isPassedMid, node.depth + 1);
                    }
                    visited[node.k - 1][newI][newJ][newNode.isPassedMid] = visited[node.k][node.i][node.j][node.isPassedMid] + 1;
                    queue.add(newNode);
                }
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25969.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new int[K + 1][N][M][2];
        pattern = new boolean[5][5];

        // 초기화
        for (int k = 0; k < K + 1; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    visited[k][i][j][0] = INF;
                    visited[k][i][j][1] = INF;
                }
            }
        }

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        // 패턴 입력 받기
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) pattern[i][j] = true;
            }
        }

        int result = bfs();

        System.out.println(result);
    }
}
