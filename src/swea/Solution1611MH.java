package swea.in;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution1611MH {

    private static class Node{
        int first;
        int second;

        public Node(int first_, int second_) {
            first = first_;
            second = second_;
        }
    }

    static private final int MAX_N = 200;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    static Queue<Node> queue = new LinkedList<>();
    static int[][] arr = new int[MAX_N][MAX_N];
    static int[][] visited = new int[MAX_N][MAX_N];
    static int[] di = { 0, 1, 1, 0, -1, -1 };
    static int[] dj = { 1, 1, 0, -1, -1, 0 };

    static void preprocessing() {
        int num = 1;
        for (int i = 0; num <= 10000; i++) {
            for (int j = 0; j <= i & num <= 10000; j++) {
                arr[i][j] = num++;
            }
        }

        int total = 1;
        map.put(1, 0);
        for (int i = 1; total <= 10000; i++) {
            total += i;
            map.put(total, i);
        }
    }

    static Node getPos(int n) {
        Map.Entry<Integer, Integer> e = map.floorEntry(n);

        int i = e.getValue();
        int j = n - e.getKey();

        return new Node(i, j);
    }

    static void init() {
        queue = new LinkedList<>();
        visited = new int[MAX_N][MAX_N];
    }

    static boolean isIn(int i, int j) {
        return i >= 0 && j >= 0;
    }

    static int solve(Node start, Node target) {
        queue.add(start);
        visited[start.first][start.second] = 1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int i = node.first;
            int j = node.second;

            if (i == target.first && j == target.second) return visited[i][j] - 1;

            for (int dir = 0; dir < 6; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (!isIn(newI, newJ)) continue;
                if (arr[newI][newJ] == 0) continue;
                if (visited[newI][newJ] != 0) continue;

                visited[newI][newJ] = visited[i][j] + 1;
                queue.add(new Node(newI, newJ));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        preprocessing();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            init();

            int ret = solve(getPos(a), getPos(b));

            System.out.println(ret);
        }
    }
}