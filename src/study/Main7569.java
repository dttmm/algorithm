package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main7569 {
    static int N;
    static int M;
    static int H;
    static int[][][] arr;
    static int[] di = {-1, 1, 0, 0, 0, 0};
    static int[] dj = {0, 0, 1, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static boolean[][][] visited;
    static int count;
    static int day;
    static Queue<Tomato> q;

    private static class Tomato {
        int h;
        int i;
        int j;

        public Tomato(int h, int i, int j) {
            this.h = h;
            this.i = i;
            this.j = j;
        }
    }

    public static boolean isIn(int newH, int newI, int newJ) {
        if (newH >= 0 && newH < H && newI >= 0 && newI < N && newJ >= 0 && newJ < M) return true;
        return false;
    }

    public static void solve() {
        while (!q.isEmpty()) {
            Tomato tomato = q.poll();
            if (tomato.h == -1) {
                if (!q.isEmpty()) {
                    day++;
                    q.add(new Tomato(-1, -1, -1));
                }
                continue;
            }
            for (int dir = 0; dir < 6; dir++) {
                int newH = tomato.h + dh[dir];
                int newI = tomato.i + di[dir];
                int newJ = tomato.j + dj[dir];
                if (isIn(newH, newI, newJ) && !visited[newH][newI][newJ] && arr[newH][newI][newJ] == 0) {
                    arr[newH][newI][newJ] = 1;
                    visited[newH][newI][newJ] = true;
                    q.add(new Tomato(newH, newI, newJ));
                    count--;
                }
            }
            if (count == 0) {
                day++;
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/7569.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());   // 가로
        N = Integer.parseInt(st.nextToken());   // 세로
        H = Integer.parseInt(st.nextToken());   // 높이

        q = new LinkedList<>();
        count = 0;
        day = 0;
        arr = new int[H][N][M];
        visited = new boolean[H][N][M];
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    arr[h][i][j] = n;

                    if (n == 1) {
                        q.add(new Tomato(h, i, j));
                        visited[h][i][j] = true;
                    }

                    if (n == 0) count++;
                }
            }
        }
        q.add(new Tomato(-1, -1, -1));

        if (count == 0) {
            System.out.println(day);
            return;
        }

        solve();

        if (count > 0) day = -1;
        System.out.println(day);

    }
}
