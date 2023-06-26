package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 설계 9분 구현 10분
 약간 벽부수기와 비슷한 느낌을 받았는데
 이 문제는 벽을 부순 최소 횟수만 구하면 됨
 즉, bfs로 최대한 이동할 수 있는 만큼 이동하고
 막혀서 진행이 안되었던 벽들을 한번에 부수고
 다시 bfs로 탐색을 하면 될 것이라 판단

 bfs를 위한 queue와
 부술 벽을 담을 queue_dark을 선언
 */
public class Main2665 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<Node> queue;
    static Queue<Node> queue_dark;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    private static class Node {
        int i;
        int j;
        int count;  // 벽을 부순 횟수

        public Node(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }
    }

    // bfs
    static int bfs() {
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 목적지 도달한 경우
            if (node.i == N - 1 && node.j == N - 1) return node.count;

            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문한 곳이면 패쓰
                if (visited[newI][newJ]) continue;
                visited[newI][newJ] = true;

                // 갈 수 있는 곳인 경우
                if (arr[newI][newJ] == 1) queue.add(new Node(newI, newJ, node.count));
                // 벽인 경우
                else queue_dark.add(new Node(newI, newJ, node.count + 1));
            }
        }

        // 벽 부수기
        while (!queue_dark.isEmpty()) {
            Node node = queue_dark.poll();
            queue.add(node);
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2665.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        queue = new LinkedList();
        queue_dark = new LinkedList();

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);

                if (c == '1') arr[i][j] = 1;
            }
        }

        // 시작 노드 설정
        queue.add(new Node(0, 0, 0));
        visited[0][0] = true;

        // 목적지 나올 때까지 bfs 반복
        int result = -1;
        while (!queue.isEmpty()) {
            result = bfs();
            if (result != -1) break;
        }

        System.out.println(result);
    }
}
