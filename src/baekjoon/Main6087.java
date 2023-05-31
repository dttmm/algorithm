package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 10분 구현 16분
 답을 찾기 위한 규칙이 보이지 않아서
 완탐을 해야되는 것으로 판단

 처음 레이저가 있는 노드에서 부터
 4방향으로 레이저를 퍼트리면서
 레이저가 닿은 노드에서도 4방향으로 계속 레이저를 퍼트리면 완탐 가능

 각 노드를 한번씩만 방문하면서
 4방향으로 탐색해 나가니까
 100*100*4 = 4만번 안에 탐색 가능

 각 노드에는 거울에 몇 번 팅긴 레이저인지 정보(depth)를 저장
 depth가 낮은 노드에서 부터 bfs를 돌리면 최소값을 더 빠르게 찾을 수 있음

 4방향 탐색을 하면서
 이전에 있던 노드와 방향이 같다면,
 직진 하는 레이저이므로 depth값은 변하지 않고
 방향이 다르다면,
 거울에 팅긴 레이저이므로 depth값을 1 증가 시킴
 */
public class Main6087 {

    static int N;
    static int M;
    static char[][] arr;
    static Node[] target;   // 레이저가 있는 위치
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static boolean[][] visited;

    private static class Node implements Comparable<Node> {
        int i;
        int j;
        int depth;  // 거울에 몇 번 팅긴 레이저인지
        int dir;

        public Node(int i, int j, int depth, int dir) {
            this.i = i;
            this.j = j;
            this.depth = depth;
            this.dir = dir;
        }

        @Override
        public int compareTo(Node o) {
            return this.depth - o.depth;
        }
    }

    // 범위 검사
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static int solve(Node startNode) {
        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(startNode);

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 레이저 있는 곳 찾은 경우
            if (arr[node.i][node.j] == 'C' && node.depth != -1) return node.depth;

            visited[node.i][node.j] = true;

            // 4방향 검사
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어난 경우 패쓰
                if (!isIn(newI, newJ)) continue;
                // 벽인 경우 패쓰
                if (arr[newI][newJ] == '*') continue;
                // 이미 방문한 곳인 경우 패쓰
                if (visited[newI][newJ]) continue;

                // 이전 노드와 방향이 같은 경우
                if (dir == node.dir) pq.add(new Node(newI, newJ, node.depth, dir));
                    // 이전 노드와 방향이 다른 경우
                else pq.add(new Node(newI, newJ, node.depth + 1, dir));
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/6087.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        target = new Node[2];
        visited = new boolean[N][M];

        int index = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                arr[i][j] = c;

                // 레이저 위치 저장
                if (c != 'C') continue;
                target[index++] = new Node(i, j, -1, -1);
            }
        }

        // 하나의 레이저 위치에서부터 탐색 시작
        int result = solve(target[0]);
        System.out.println(result);
    }
}
