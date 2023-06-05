package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 31분 구현 2시간+
 더러운 구현 문제
 가장 큰 관건은
 1. 클러스터를 어떻게 묶을 것인가
 2. 클러스터를 어떻게 움직일 것인가

 1번
 bfs를 이용해서 클러스터를 구할 수 있는데
 클러스터에 포함된 미네랄들만 따로 움직여야하기 때문에
 클러스터의 정보를 따로 저장할 필요가 있었음
 클러스터의 정보를 큐에 넣고 하나씩 미네랄을 빼면서 이동해주는 방법을 떠올림

 2번
 클러스터를 이동하기 위해서는 얼마나 이동해야할지를 구해야함
 클러스터 정보가 담긴 큐를 돌면서
 각 노드에서 아래로 내려가면서 이동할 수 있는 높이를 구해주고
 가능한 높이중 최소값만큼 클러스터를 이동해줌
 근데 큐에서 클러스터의 정보를 빼면서 미네랄을 이동하게 되면
 높이가 높은 미네랄을 먼저 이동하는 경우
 아래에 있는 미네랄의 결과에 영향을 주게됨
 즉, 높이가 낮은 미네랄부터 움직여야 결과가 이상해지지 않음
 그래서 pq도 추가함

 그냥 고려할게 너무 많다보니 복잡함
 문제 너무 더럽
 */
public class Main2933 {

    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited; // bfs에 중복 검사 || 클러스터 위치 정보 담을 배열
    static Queue<Node> clusterQ;    // 클러스터 정보 담을 큐
    static PriorityQueue<Node> clusterPQ;   // 클러스터 움직일 때 사용할 pq
    static Queue<Node> q;   // bfs에 사용할 큐
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node implements Comparable<Node> {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        // i가 높은(높이가 낮은) 미네랄부터 움직이기 위함
        @Override
        public int compareTo(Node o) {
            return -(this.i - o.i);
        }
    }

    // 범위 벗어났는지 검사
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 미네랄 부시기
    static void crash(int i, int type) {
        // 왼쪽에서 부시기
        if (type % 2 == 0) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) continue;

                arr[i][j] = 0;
                findTarget(i, j);
                break;
            }
        }
        // 오른쪽에서 부시기
        else {
            for (int j = M - 1; j >= 0; j--) {
                if (arr[i][j] == 0) continue;

                arr[i][j] = 0;
                findTarget(i, j);
                break;
            }
        }
    }

    // 움직일 클러스터 찾기
    static void findTarget(int i, int j) {

        // 4방향 탐색
        for (int dir = 0; dir < 4; dir++) {
            int newI = i + di[dir];
            int newJ = j + dj[dir];

            // 범위 벗어난 경우 패쓰
            if (!isIn(newI, newJ)) continue;
            // 빈 공간인 경우 패쓰
            if (arr[newI][newJ] == 0) continue;

            // 해당 미네랄과 연결된 클러스터 이동
            boolean result = bfs(newI, newJ);
            // 클러스터 이동했다면 탈출 <- 이동 가능한 클러스터는 하나이기 떄문
            if (result) break;
        }
    }

    // 클러스터 탐색
    static boolean bfs(int start_i, int start_j) {
        q = new LinkedList();
        clusterQ = new LinkedList();
        clusterPQ = new PriorityQueue();
        visited = new boolean[N][M];

        Node newNode = new Node(start_i, start_j);
        q.add(newNode);
        clusterQ.add(newNode);
        clusterPQ.add(newNode);
        visited[start_i][start_j] = true;
        int bottom = N - start_i;

        while (!q.isEmpty()) {
            Node node = q.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;
                // 빈 공간이면 패쓰
                if (arr[newI][newJ] == 0) continue;

                newNode = new Node(newI, newJ);
                q.add(newNode);
                clusterQ.add(newNode);
                clusterPQ.add(newNode);
                visited[newI][newJ] = true;
                bottom = Math.min(bottom, N - newI);
            }
        }

        // 클러스터가 바닥에 닿은 경우 -> 이동 불가 -> 다른 클러스터 탐색
        if (bottom == 1) return false;

        // 아래로 이동할 높이 구하기
        int height = findHeight();

        // 아래로 이동
        move(height);

        return true;
    }

    // 아래로 이동할 높이 구하기
    static int findHeight() {
        int height = N;

        while (!clusterQ.isEmpty()) {
            Node node = clusterQ.poll();
            int i = node.i;
            int j = node.j;

            // 해당 노드에서 이동할 수 있는 최대 높이 구하기
            int diff = 0;
            for (int x = i + 1; x <= N; x++) {
                diff = Math.max(diff, x - i);

                // 끝에 도달한 경우
                if (x == N) break;

                // 같은 클러스터인 경우
                if (visited[x][j]) {
                    diff = N;
                    break;
                }

                // 다른 클러스터인 경우
                if (arr[x][j] == 1) break;
            }

            // 모든 노드에서 이동 가능한 높이중에 최소값 구하기
            height = Math.min(height, diff);
        }

        // 값 보정 해줌
        return height - 1;
    }

    // 아래로 이동
    static void move(int height) {
        // i가 큰 것부터(높이가 낮은 것 부터)이동
        while (!clusterPQ.isEmpty()) {
            Node node = clusterPQ.poll();
            int i = node.i;
            int j = node.j;

            // 해당 높이만큼 이동
            arr[i + height][j] = arr[i][j];
            arr[i][j] = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2933.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                if (c == 'x') arr[i][j] = 1;
            }
        }

        // 미네랄 부시기
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {
            int floor = Integer.parseInt(st.nextToken());
            crash(N - floor, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) sb.append('x');
                else sb.append('.');
            }
            sb.append("\n");
        }

        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }
}
