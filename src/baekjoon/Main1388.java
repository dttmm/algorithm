package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 3분 구현 10분
 bfs
 입력 받은 판자를 구분하여
 조건 분기 처리해줌
 판자가 -모양이라면
 bfs에서 4방향 탐색할 때 좌우만 탐색하도록하고
 해당 위치의 판자가 같은 모양일때만 이어서 탐색하도록함
 |모양의 경우도 마찬가지로 조건 분기 탐색함
 마직마에 bfs를 얼마나 수행했는지 계산하면 정답쓰
 */
public class Main1388 {

    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    // 범위 체크
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
        visited[start_i][start_j] = true;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                // 판자가 -모양일 경우
                if (arr[i][j] == '-') {
                    // 좌우만 탐색
                    if (dir % 2 == 0) continue;
                    if (arr[newI][newJ] != '-') continue;
                }
                // 판자가 | 모양일 경우
                else {
                    // 상하만 탐색
                    if (dir % 2 == 1) continue;
                    if (arr[newI][newJ] != '|') continue;
                }

                visited[newI][newJ] = true;
                queue_i.add(newI);
                queue_j.add(newJ);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1388.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
            }
        }

        // bfs 수행
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                solve(i, j);
                count++;
            }
        }

        System.out.println(count);
    }
}
