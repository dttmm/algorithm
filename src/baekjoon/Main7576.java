package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main7576 {
    static int N;
    static int M;
    static int[][] arr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static Queue<Tomato> queue;
    static int rest;

    private static class Tomato {
        int i;
        int j;

        public Tomato(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < M) return true;
        return false;
    }

    public static int solve() {
        int count = 0;
        while (!queue.isEmpty()) {
            Tomato tomato = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newI = tomato.i + di[dir];
                int newJ = tomato.j + dj[dir];
                if (isIn(newI, newJ) && arr[newI][newJ] == 0) {
                    arr[newI][newJ] = arr[tomato.i][tomato.j] + 1;
                    count = arr[tomato.i][tomato.j] + 1;
                    queue.add(new Tomato(newI, newJ));
                    rest--;
                }
            }
            if (rest == 0) break;
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7576.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        queue = new LinkedList();
        rest = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
                if (n == 1) {
                    queue.add(new Tomato(i, j));
                } else if (n == 0) {
                    rest++;
                }
            }
        }

        if (rest == 0) {
            System.out.println(0);
            return;
        }

        int answer = solve() - 1;
        if (rest > 0) System.out.println(-1);
        else System.out.println(answer);

    }
}
