package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2178 {
    static int N;
    static int M;
    static int[][] arr;
    static int[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < M && arr[newI][newJ] == 1) return true;
        return false;
    }

    public static int bfs() {
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        queue.add(0);
        visited[0][0] = 1;
        int answer = 0;

        while (!queue.isEmpty()) {
            int i = queue.poll();
            int j = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && visited[newI][newJ] == 0) {
                    visited[newI][newJ] = visited[i][j] + 1;
                    answer = visited[i][j];
                    queue.add(newI);
                    queue.add(newJ);
                    if (newI == N - 1 && newJ == M - 1) return answer;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2178.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        System.out.println(bfs() + 1);
    }
}
