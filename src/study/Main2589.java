package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2589 {
    static int N;
    static int M;
    static char[][] arr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) return true;
        return false;
    }

    public static int bfs(int i, int j) {
        int max = 0;
        int[][] visited = new int[N][M];
        visited[i][j] = 1;
        int[] q = new int[100000];
        int front = -1;
        int rear = -1;
        q[++rear] = i;
        q[++rear] = j;
        while (front != rear) {
            i = q[++front];
            j = q[++front];
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && arr[newI][newJ] == 'L' && visited[newI][newJ] == 0) {
                    q[++rear] = newI;
                    q[++rear] = newJ;
                    visited[newI][newJ] = visited[i][j] + 1;
                    max = Math.max(visited[i][j] + 1, max);
                }

            }
        }
        return max - 1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/study/2589.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로
        arr = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), "");
            String s = st.nextToken();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 'L') {
                    int count = bfs(i, j);
                    max = Math.max(count, max);
                }
            }
        }
        System.out.println(max);
    }
}
