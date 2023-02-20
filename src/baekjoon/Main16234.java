package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 생각보다 복잡한 구현 문제
 매번 배열을 탐색하면서
 bfs로 연결할 수 있는 나라들을 연결하고
 연결된 나라는 인구 분배를 진행

 이 과정을 반복
 만약 인구 분배가 한번이라도 이루어지지 않은 경우에는
 그만 끝냄
 */
public class Main16234 {

    static int N;
    static int L;
    static int R;
    static Node[][] arr;
    static boolean[][] visited;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node {
        int i;
        int j;
        int num;

        public Node(int i, int j, int num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
    }

    public static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    // 연결 가능한 나라들 연결
    public static boolean bfs(int i, int j) {
        Queue<Node> queue = new LinkedList();
        queue.add(arr[i][j]);
        visited[i][j] = true;

        Set<Node> set = new HashSet();  // 연결한 나라 담음
        set.add(arr[i][j]);
        int total = arr[i][j].num;  // 연결한 나라들의 인구수 합

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                int diff = Math.abs(arr[node.i][node.j].num - arr[newI][newJ].num);
                // 조건 안맞으면 패쓰
                if (diff < L || diff > R) continue;

                queue.add(arr[newI][newJ]);
                set.add(arr[newI][newJ]);
                total += arr[newI][newJ].num;
                visited[newI][newJ] = true;
            }
        }

        // 연결된 나라가 하나도 없는 경우
        if (set.size() == 1) return false;

        // 연결된 나라들끼리 인구 분배
        int result = total / set.size();
        for (Node node : set) {
            node.num = result;
        }
        return true;
    }

    public static int solve() {
        int count = 0;
        boolean flag = false;   // 인구 이동 일어났는지 확인 플래그 false: 인구이동 한 번도 안일어남

        while (count < 2000) {

            flag = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    boolean result = bfs(i, j);

                    // 인구 분배 이루어진 경우 플래그 변경
                    if (result) flag = true;
                }
            }

            // 인구분배가 한번도 이루어지지 않은 경우
            if (!flag) break;

            count++;
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16234.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new Node[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());

                arr[i][j] = new Node(i, j, n);
            }
        }

        System.out.println(solve());
    }
}
