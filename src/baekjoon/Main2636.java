package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2636 {

    static int N;
    static int M;
    static boolean[][] arr;
    static int totalCount;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, 1, -1};
    static boolean[][] visited;
    static LinkedList<Node> meltList;
    static int lastCount;

    private static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static void bfs(int i, int j) {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(i, j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            i = node.i;
            j = node.j;
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (isIn(newI, newJ) && !visited[newI][newJ]) {
                    visited[newI][newJ] = true;
                    Node newNode = new Node(newI, newJ);

                    if (arr[newI][newJ]) {
                        meltList.add(newNode);
                    } else {
                        queue.add(newNode);
                    }
                }
            }
        }
    }

    private static void melt() {
        while (!meltList.isEmpty()) {
            Node node = meltList.poll();
            arr[node.i][node.j] = false;
            totalCount--;
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2636.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        totalCount = 0;

        arr = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());

                if (n == 1) {
                    arr[i][j] = true;
                    totalCount++;
                }
            }
        }

        int time = 0;

        while (totalCount != 0) {
            lastCount = totalCount;
            visited = new boolean[N][M];
            meltList = new LinkedList();

            bfs(0, 0);

            melt();
            time++;
        }

        System.out.println(time + "\n" + lastCount);
    }
}
