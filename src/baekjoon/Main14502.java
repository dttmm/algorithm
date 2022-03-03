package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14502 {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] tr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static int COUNT;
    static int max;
    static boolean[][] q_visited;

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) return true;
        return false;
    }

    public static int bfs(int vi, int vj) {
        int sum = 0;
        int[] qi = new int[1000];
        int[] qj = new int[1000];
        int front = -1;
        int rear = -1;
        qi[++rear] = vi;
        qj[rear] = vj;
        while (front != rear) {
            vi = qi[++front];
            vj = qj[front];
            for (int dir = 0; dir < 4; dir++) {
                int newI = vi + di[dir];
                int newJ = vj + dj[dir];
                if (isIn(newI, newJ) && !q_visited[newI][newJ] && arr[newI][newJ] == 0) {
                    qi[++rear] = newI;
                    qj[rear] = newJ;
                    sum++;
                    q_visited[newI][newJ] = true;
                }
            }
        }
        return sum;
    }

    public static void c(int k, int s) {
        if (k == 3) {
            for (int x = 0; x < 3; x++) {
                int i = tr[2 * x];
                int j = tr[2 * x + 1];
                arr[i][j] = 1;
            }

            int count = COUNT - 3;
            q_visited = new boolean[N][M];
            for (int x = 0; x < N * M; x++) {
                int i = x / M;
                int j = x % M;
                if (arr[i][j] == 2) {
                    count -= bfs(i, j);
                }
            }
            if (count > max){
                max = count;
            }

            for (int x = 0; x < 3; x++) {
                int i = tr[2 * x];
                int j = tr[2 * x + 1];
                arr[i][j] = 0;
            }

        } else {
            while (s < N * M) {
                int i = s / M;
                int j = s % M;
                if (arr[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    tr[2 * k] = i;
                    tr[2 * k + 1] = j;
                    c(k + 1, s + 1);
                    visited[i][j] = false;
                }
                s++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14502.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        tr = new int[6];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        COUNT = 0;
        max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) COUNT++;
            }
        }

        c(0, 0);

        System.out.println(max);
    }
}
