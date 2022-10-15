package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main10026 {

    static int N;
    static char[][] arr1;
    static char[][] arr2;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    static boolean isIn(int i, int j) {
        if (i < N && i >= 0 && j < N && j >= 0) return true;
        return false;
    }

    public static void bfs(char start_c, int start_i, int start_j, char[][] arr) {
        Queue<Character> queue = new LinkedList();
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue.add(start_c);
        queue_i.add(start_i);
        queue_j.add(start_j);

        while (!queue.isEmpty()) {
            char c = queue.poll();
            int i = queue_i.poll();
            int j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && !visited[newI][newJ] && arr[newI][newJ] == start_c) {
                    queue.add(arr[newI][newJ]);
                    queue_i.add(newI);
                    queue_j.add(newJ);
                    visited[newI][newJ] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10026.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr1 = new char[N][N];
        arr2 = new char[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                arr1[i][j] = c;
                arr2[i][j] = c == 'G' ? 'R' : c;
            }
        }

        visited = new boolean[N][N];
        int count1 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(arr1[i][j], i, j, arr1);
                    count1++;
                }
            }
        }

        visited = new boolean[N][N];
        int count2 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(arr2[i][j], i, j, arr2);
                    count2++;
                }
            }
        }

        System.out.println(count1 + " " + count2);
    }
}
