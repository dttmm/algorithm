package 대회.YEAR_2023;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 간단한 4방향 탐색 bfs문제
 범위를 벗어나는 경우에만
 어떻게 처리를 해줄지 결정하면 끝
 visited를 따로 만들지 않고
 arr에서 1을 이용하여 방문처리 하였음
 */
public class MainB {

    static int N;
    static int M;
    static int[][] arr;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위를 벗어난 경우
    static int[] isOver(int i, int j) {
        int[] point = {i, j};

        if (i < 0) point[0] = N - 1;
        else if (i >= N) point[0] = 0;

        if (j < 0) point[1] = M - 1;
        else if (j >= M) point[1] = 0;

        return point;
    }

    static void bfs(int start_i, int start_j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(start_i);
        queue_j.add(start_j);

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                int[] point = isOver(newI, newJ);
                newI = point[0];
                newJ = point[1];

                if (arr[newI][newJ] == 1) continue;
                arr[newI][newJ] = 1;
                queue_i.add(newI);
                queue_j.add(newJ);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/대회/YEAR_2023/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) continue;
                bfs(i, j);
                count++;
            }
        }

        System.out.println(count);
    }
}
