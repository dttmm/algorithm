package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2667 {
    static int N;
    static int[][] arr;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};

    public static boolean isIn(int newI, int newJ) {
        if (newI >= 0 && newI < N && newJ >= 0 && newJ < N) return true;
        return false;
    }

    public static int bfs(int i, int j) {
        int count = 0;
        int[] qi = new int[1000];
        int[] qj = new int[1000];
        int front = -1;
        int rear = -1;
        qi[++rear] = i;
        qj[rear] = j;
        arr[i][j] = 0;
        while (front != rear) {
            i = qi[++front];
            j = qj[front];
            count++;
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];
                if (isIn(newI, newJ) && arr[newI][newJ] == 1) {
                    qi[++rear] = newI;
                    qj[rear] = newJ;
                    arr[newI][newJ] = 0;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2667.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        int total = 0;
        List<Integer> dan = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    dan.add(bfs(i, j));
                    total++;
                }
            }
        }
        Collections.sort(dan);

        System.out.println(total);
        for (int i : dan) {
            System.out.println(i);
        }
    }
}
