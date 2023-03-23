package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 처음에는 bfs로 접근했는데
 방문처리를 위한 백트래킹이 필요해서
 dfs로 경로 변경

 재귀를 돌릴 때마다
 방문처리 배열을 복사해서 넘기지 않고
 방문처리 배열 하나만 사용해서
 true false 변경해가며
 메모리 공간 줄임
 */
public class Main1987 {

    static int R;
    static int C;
    static char[][] arr;
    static boolean[][] visited;
    static boolean[] visitedAlpha;  // 알파벳 방문 검사
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static int max;

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < R && j >= 0 && j < C) return true;
        return false;
    }

    // dfs
    static void solve(int i, int j, int count) {
        visited[i][j] = true;
        visitedAlpha[arr[i][j] - 'A'] = true;

        // 최대값 찾기
        max = Math.max(max, count);

        for (int dir = 0; dir < 4; dir++) {
            int newI = i + di[dir];
            int newJ = j + dj[dir];

            // 범위 벗어나면 패쓰
            if (!isIn(newI, newJ)) continue;
            // 이미 방문한 곳이면 패쓰
            if (visited[newI][newJ]) continue;
            // 이미 방문한 알파벳이면 패쓰
            if (visitedAlpha[arr[newI][newJ] - 'A']) continue;

            solve(newI, newJ, count + 1);
        }

        visited[i][j] = false;
        visitedAlpha[arr[i][j] - 'A'] = false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1987.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new boolean[R][C];
        visitedAlpha = new boolean[26];
        max = 0;

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
            }
        }

        solve(0, 0, 1);
        System.out.println(max);
    }
}
