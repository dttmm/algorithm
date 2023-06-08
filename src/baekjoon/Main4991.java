package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 설계 39분 구현 16분
 백트래킹 + bfs
 최소값을 구하는 공식이나 패턴이 있을까 찾아봄
 패턴 없어보임
 결국 모든 경우의 수를 고려하는 방향으로 설계함
 현재 있는 지점에서 bfs를 통해 더러운 칸으로 갈 수 있는 경우의 수를 구하고
 경우의 수 중 하나를 선택하여
 선택한 경우에서 bfs를 통해 또 다른 더러운 칸으로 갈 수 있는 경우의 수를 구하면서
 순열 -> 하나 고르고 또 순열 -> 하나 고르고 또 순열 반복하면서 백트래킹으로 최소값 찾음
 */
public class Main4991 {

    static int N;
    static int M;
    static int[][] arr;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static int count;   // 전체 더러운 칸 개수
    static int min;     // 최소값

    private static class Node {
        int i;
        int j;
        int d;

        public Node(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    // 범위 벗어났는지 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 해당 노드에서 모든 더러운 곳까지의 거리 구함
    static List<Node> bfs(Node startNode) {
        Queue<Node> queue = new LinkedList();
        boolean[][] visited = new boolean[N][M];
        queue.add(startNode);
        visited[startNode.i][startNode.j] = true;
        List<Node> list = new ArrayList();  // 더러운 곳 저장할 리스트

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 벽이면 패쓰
                if (arr[newI][newJ] == -1) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                visited[newI][newJ] = true;
                Node newNode = new Node(newI, newJ, node.d + 1);
                queue.add(newNode);

                // 더러운 곳인 경우
                if (arr[newI][newJ] == 1) list.add(newNode);
            }
        }
        return list;
    }

    // 순열
    static void solve(int k, int sum, Node startNode) {
        // 백트래킹 조건
        if (sum >= min) return;

        // 더러운 곳 다 탐색한 경우
        if (k == count) {
            min = sum;
        } else {
            // 현재 노드에서 갈 수 있는 더러운 곳 리스트 받음
            List<Node> list = bfs(new Node(startNode.i, startNode.j, 0));
            // 갈 수 없는 더러운 곳이 있는 경우
            if (list.size() != count - k) return;

            // 순열 하나 뽑음
            for (int i = 0; i < list.size(); i++) {
                Node node = list.get(i);
                arr[node.i][node.j] = 0;
                solve(k + 1, sum + node.d, node);
                arr[node.i][node.j] = 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4991.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            arr = new int[N][M];
            count = 0;
            min = Integer.MAX_VALUE;

            int start_i = -1;
            int start_j = -1;

            // 입력 받기
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);

                    // 벽
                    if (c == 'x') arr[i][j] = -1;
                    // 더러운 곳
                    else if (c == '*') {
                        arr[i][j] = 1;
                        count++;
                    }
                    // 시작 지점
                    else if (c == 'o') {
                        start_i = i;
                        start_j = j;
                    }
                }
            }

            // 청소 시작
            solve(0, 0, new Node(start_i, start_j, 0));

            // 예외 처리
            if (min == Integer.MAX_VALUE) sb.append("-1\n");
            else sb.append(min + "\n");

            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }
}
