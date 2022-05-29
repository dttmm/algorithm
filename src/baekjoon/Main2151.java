package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main2151 {
    static int N;
    static char[][] arr;
    static PriorityQueue<Mirror> mirror;
    static Queue<Integer> door;
    static int endI;
    static int endJ;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    private static class Mirror implements Comparable<Mirror> {
        int i;
        int j;
        int dir;
        int count;

        public Mirror(int i, int j, int dir, int count) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public int compareTo(Mirror o) {
            return this.count - o.count;
        }
    }

    public static int isIn(int newI, int newJ) {
        if (newI < 0 || newI >= N || newJ < 0 || newJ >= N) return -1;
        char c = arr[newI][newJ];
        if (c == '*') return -1;
        else if (c == '#') return 1;
        else if (c == '!') return 2;
        return 0;
    }


    public static int solve() {

        while (true) {
            Mirror m = mirror.poll();
            int i = m.i;
            int j = m.j;
            int dir = m.dir;
            int count = m.count;

            i = i + di[dir];
            j = j + dj[dir];

            int state = isIn(i, j);

            switch (dir) {
                case 0: // 위 방향
                case 1: // 아래 방향
                    while (state != 1) {
                        if (state == -1) {
                            break;
                        } else if (state == 0) {    // 빈 공간일 경우
                            i = i + di[dir];
                            j = j + dj[dir];
                            state = isIn(i, j);
                            continue;
                        } else { // 거울을 놓을 수 있을 경우
                            mirror.add(new Mirror(i, j, dir, count));

                            mirror.add(new Mirror(i, j, 2, count + 1));

                            mirror.add(new Mirror(i, j, 3, count + 1));

                            break;
                        }
                    }
                    break;
                case 2: // 왼 방향
                case 3: // 오른 방향
                    while (state != 1) {
                        if (state == -1) {
                            break;
                        } else if (state == 0) {    // 빈 공간일 경우
                            i = i + di[dir];
                            j = j + dj[dir];
                            state = isIn(i, j);
                            continue;
                        } else { // 거울을 놓을 수 있을 경우
                            mirror.add(new Mirror(i, j, dir, count));

                            mirror.add(new Mirror(i, j, 0, count + 1));

                            mirror.add(new Mirror(i, j, 1, count + 1));

                            break;
                        }
                    }
                    break;
            }
            if (state == 1 && i == endI && j == endJ) return count;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2151.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        mirror = new PriorityQueue<>();
        door = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
                if (c == '#') {
                    door.add(i);
                    door.add(j);
                }
            }
        }

        int startI = door.poll();
        int startJ = door.poll();
        endI = door.poll();
        endJ = door.poll();

        mirror.add(new Mirror(startI, startJ, 0, 0));
        mirror.add(new Mirror(startI, startJ, 1, 0));
        mirror.add(new Mirror(startI, startJ, 2, 0));
        mirror.add(new Mirror(startI, startJ, 3, 0));

        System.out.println(solve());
    }
}
