package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main9376 {

    static int N;
    static int M;
    static int[][] arr;
    static Node[][] d;
    static Node[] start;
    static boolean[][] visited;
    static PriorityQueue<Node> pq;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node implements Comparable<Node> {
        int i;
        int j;
        int depth;

        public Node(int i, int j, int depth) {
            this.i = i;
            this.j = j;
            this.depth = depth;
        }

        @Override
        public int compareTo(Node o) {
            return this.depth - o.depth;
        }
    }

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static int solve() {
        int count = 0;

        count += bfs(start[0]);
        count += bfs(start[1]);

        return count;
    }

    static int bfs(Node startNode) {
        visited = new boolean[N][M];
        pq = new PriorityQueue();
        visited[startNode.i][startNode.j] = true;
        pq.add(startNode);
        d[startNode.i][startNode.j] = new Node(-1, -1, -1);
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                if (!isIn(newI, newJ)) {
                    int count = openDoor(node);
                    return count;
                }

                if (visited[newI][newJ]) continue;
                if (arr[newI][newJ] == -1) continue;

                if (arr[newI][newJ] == 1) {
                    Node newNode = new Node(newI, newJ, node.depth + 1);
                    pq.add(newNode);
                } else {
                    Node newNode = new Node(newI, newJ, node.depth);
                    pq.add(newNode);
                }
                visited[newI][newJ] = true;
                d[newI][newJ] = node;
            }
        }
        return 0;
    }

    static int openDoor(Node node) {
        int count = 0;
        while (node.i != -1) {
            if (arr[node.i][node.j] == 1) {
                arr[node.i][node.j] = 0;
                count++;
            }
            node = d[node.i][node.j];
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9376.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N][M];
            d = new Node[N][M];
            start = new Node[2];

            int index = 0;
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);

                    if (c == '*') arr[i][j] = -1;
                    else if (c == '#') arr[i][j] = 1;
                    else if (c == '$') {
                        Node node = new Node(i, j, 0);
                        start[index] = node;
                        index++;
                    }
                }
            }

            int count = solve();
            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
}
