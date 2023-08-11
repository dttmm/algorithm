package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 1분 구현 5분
 bfs
 2방향 탐색을 하면서 bfs 돌리고
 목적지 나오면 solve함수 리턴함
 */
public class Main16173 {

    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] di = {0, 1};
    static int[] dj = {1, 0};

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    // bfs
    static boolean solve() {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(0);
        queue_j.add(0);
        visited[0][0] = true;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            // 목적지 도착한 경우
            if (i == N - 1 && j == N - 1) return true;

            // 2방향 탐색
            for (int dir = 0; dir < 2; dir++) {
                int newI = i + di[dir] * arr[i][j];
                int newJ = j + dj[dir] * arr[i][j];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                visited[newI][newJ] = true;
            }
        }

        // 목적지에 도착 못한 경우
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16173.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        // 목적지 도착 여부 반환
        boolean result = solve();

        if (result) System.out.println("HaruHaru");
        else System.out.println("Hing");
    }
}
