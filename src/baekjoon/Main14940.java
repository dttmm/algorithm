package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 3분 구현 10분
 bfs 문제
 시작 지점에서부터 bfs 돌리면서
 깊이를 d배열에 저장해줌
 방문 처리는 arr배열에서 이미 방문한 곳을 0으로 처리함

 예외 하나가 문제 마지막 떡하니 있었네
 문제를 끝까지 잘 읽자
 */
public class Main14940 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] d;   // 거리 담을 배열
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 벗어났는지 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static void solve(int start_i, int start_j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(start_i);
        queue_j.add(start_j);

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 갈 수 없는 길이거나 이미 방문한 곳이면 패쓰
                if (arr[newI][newJ] == 0) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                arr[newI][newJ] = 0;
                d[newI][newJ] = d[i][j] + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14940.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        d = new int[N][M];

        int start_i = -1;
        int start_j = -1;

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 0) continue;

                // 시작 지점인 경우
                if (n == 2) {
                    start_i = i;
                    start_j = j;
                    continue;
                }

                arr[i][j] = 1;
            }
        }

        solve(start_i, start_j);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 갈 수 있는 길인데 도달할 수 없는 경우
                if (arr[i][j] == 1 && d[i][j] == 0) sb.append("-1 ");
                else sb.append(d[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
