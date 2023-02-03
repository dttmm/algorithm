package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 처음에는 벽을 부순 경우와 안 부순 경우 재귀를 돌면서 풀었었음
 근데 맵의 크기가 1000*1000인 경우
 최악의 경우 1000*1000^2번 탐색을 하게 되어
 1,000,000,000,000 -> 1조번 탐색을 하게됨

 아니면 있는 벽들 중에서 하나를 골라 부셔가며
 탐색을 할까 했지만
 똑같이 시간 초과가 나게됨

 도저히 아이디어가 안 떠올라서
 도움!!
 
 bfs를 이렇게 활용하는 방법은 또 처음 알았네
 */
public class Main2206 {

    static int N;
    static int M;
    static int[][] arr;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    private static class Node {
        int i;
        int j;
        int used;   // 벽을 부순 적이 있는지

        public Node(int i, int j, int used) {
            this.i = i;
            this.j = j;
            this.used = used;
        }
    }

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static int solve() {
        int[][][] visited = new int[N][M][2];
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (node.i == N - 1 && node.j == M - 1) {
                min = Math.min(min, visited[node.i][node.j][node.used]);
            }

            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                if (!isIn(newI, newJ)) continue;
                if (visited[newI][newJ][node.used] > 0) continue;

                // 벽이 있는데 아직 벽을 부술 수 있는 횟수가 남아있는 경우
                if (arr[newI][newJ] == 1 && node.used == 0) {
                    visited[newI][newJ][1] = visited[node.i][node.j][node.used] + 1;
                    queue.add(new Node(newI, newJ, 1));
                }
                // 벽이 아닌 경우
                else if (arr[newI][newJ] == 0) {
                    visited[newI][newJ][node.used] = visited[node.i][node.j][node.used] + 1;
                    queue.add(new Node(newI, newJ, node.used));
                }
            }
        }

        if (min == Integer.MAX_VALUE) min = -1;
        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2206.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c - '0';
            }
        }

        System.out.println(solve());
    }
}
