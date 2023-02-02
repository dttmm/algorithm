package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 전형적인 구현 문제
 bfs 돌면서
 4방향 탐색하면서 치즈가 공기와 몇 번 접촉했는지 count배열에 담아주고
 탐색이 다 끝나면 count배열에서 2이상인 것들만
 원래 배열arr에서 1->0으로 치즈 녹여주고
 전체 치즈 개수--해줌

 테두리에는 치즈가 놓이지 않으므로
 남은 치즈가 없을 때까지 0,0에서 bfs 계속 돌려줌줌 */
public class Main2638 {

    static int N;
    static int M;
    static int[][] arr;
    static int total;   // 전체 치즈 개수
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static void bfs(int i, int j) {
        int[][] count = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(i);
        queue_j.add(j);
        visited[i][j] = true;

        while (!queue_i.isEmpty()) {
            i = queue_i.poll();
            j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (!isIn(newI, newJ)) continue;

                // 주변이 공기인 경우
                if (arr[newI][newJ] == 0) {
                    if (visited[newI][newJ]) continue;

                    visited[newI][newJ] = true;
                    queue_i.add(newI);
                    queue_j.add(newJ);
                }
                // 주변이 치즈인 경우
                else {
                    count[newI][newJ]++;
                }
            }
        }

        // 공기와 두면 이상 접촉한 경우
        for (int ii = 0; ii < N; ii++) {
            for (int jj = 0; jj < M; jj++) {
                if (count[ii][jj] >= 2) {
                    arr[ii][jj] = 0;
                    total--;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2638.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        total = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());

                arr[i][j] = n;

                if (n == 1) total++;
            }
        }

        int time = 0;
        while (total > 0) {
            bfs(0, 0);
            time++;
        }

        System.out.println(time);
    }
}
