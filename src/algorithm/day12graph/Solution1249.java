package algorithm.day12graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간9분+50분
public class Solution1249 {
    static int[][] arr;
    static boolean[][] visited;
    static int N;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static int[][] D;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    // 모든 노드 방문했는지 확인
    public static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    continue;
                }
                if (!visited[i][j]) return true;
            }
        }
        return false;
    }

    // 현재 경로가 최소인 녀석 반환
    public static void pick(int[] minD) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    if (i == N - 1 && j == N - 1) {
                        continue;
                    }
                    if (D[i][j] < min) {
                        min = D[i][j];
                        minD[0] = i;
                        minD[1] = j;
                    }
                }
            }
        }
    }

    public static void solve(int i, int j) {
        visited[i][j] = true;
        for (int dir = 0; dir < 4; dir++) {
            int newI = i + di[dir];
            int newJ = j + dj[dir];
            if (isIn(newI, newJ)) {
                D[newI][newJ] = arr[newI][newJ];
            }
        }

        while (check()) {
            int[] minD = new int[2];
            pick(minD);
            i = minD[0];
            j = minD[1];
            visited[i][j] = true;
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && !visited[newI][newJ]) {
                    if (D[i][j] + arr[newI][newJ] < D[newI][newJ]) {
                        D[newI][newJ] = D[i][j] + arr[newI][newJ];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day12_1249.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];
            D = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    D[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }

            solve(0, 0);

            System.out.println("#" + test_case + " " + D[N - 1][N - 1]);
        }
    }
}