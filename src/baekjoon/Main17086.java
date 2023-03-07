package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 단순 bfs문제
 초기 상어 위치를 큐에 넣고
 큐를 이용해서 bfs돌림
 마지막 원소의 visited가 정답
 */
public class Main17086 {

    static int N;
    static int M;
    static Queue<Integer> queue_i;
    static Queue<Integer> queue_j;
    static int[][] visited;
    static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
    static int answer;

    // 범위 안에 있는지 검사
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static void solve() {
        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            // 마지막 원소가 정답
            answer = visited[i][j];

            // 8방향 탐색
            for (int dir = 0; dir < 8; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어난 경우 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문한 경우 패쓰
                if (visited[newI][newJ] > 0) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                visited[newI][newJ] = visited[i][j] + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17086.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        queue_i = new LinkedList();
        queue_j = new LinkedList();
        visited = new int[N][M];
        answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0) continue;

                // 상어일 경우에만 아래 작업 수행
                queue_i.add(i);
                queue_j.add(j);
                visited[i][j] = 1;
            }
        }

        solve();

        System.out.println(answer - 1);
    }
}
