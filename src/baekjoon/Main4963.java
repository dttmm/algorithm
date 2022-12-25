package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 기본적인 bfs문제
 간단하게 8방향 탐색 해주면 끝
 전체 배열을 돌면서
 섬이면서 방문하지 않은 섬인 경우
 해당 위치에서 bfs를 돌림
 전체 배열에 대해 bfs 탐색 끝나면
 bfs 탐색 시도한 횟수가 바로 정답(이어져 있는 섬의 개수)
 */
public class Main4963 {

    static int w;
    static int h;
    static int[][] arr;
    static boolean[][] visited;
    static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < h && j >= 0 && j < w) return true;
        return false;
    }

    static void solve(int i, int j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(i);
        queue_j.add(j);
        visited[i][j] = true;

        while (!queue_i.isEmpty()) {
            i = queue_i.poll();
            j = queue_j.poll();

            for (int dir = 0; dir < 8; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위를 벗어난 경우 패쓰
                if (!isIn(newI, newJ)) continue;
                // 바다인 경우 패쓰
                if (arr[newI][newJ] == 0) continue;
                // 이미 방문한 경우 패쓰
                if (visited[newI][newJ]) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                visited[newI][newJ] = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4963.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            arr = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    // 바다인 경우 패쓰
                    if (arr[i][j] == 0) continue;
                    // 이미 방문한 경우 패쓰
                    if (visited[i][j]) continue;

                    solve(i, j);
                    count++;
                }
            }
            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
    }
}
