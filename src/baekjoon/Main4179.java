package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 4분 구현 15분 디버깅 9분
 명지쓰의 질문에 답하기 위해 풀어봄
 bfs로 이동하여 지훈이가 이동하는 지 불이 이동하는지에 따라 분기처리 해줌
 지훈이의 방문 처리는 visited배열에 하고
 불의 방문 처리는 기존 arr배열에서 불이 지나간 자리는 벽이라고 표시함
 지훈이가 배열 범위 밖으로 벗어나면 탈출 성공!

 틀림
 로직이 잘못 되었나 싶어 확인해봤는데
 틀린 로직이 없음
 알고보니 입력에서 지훈이는 하나만 주어지는데
 불은 여러개 주어질 수도 있었음
 불도 하나만 주어지는 줄 알았음..
 */
public class Main4179 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] visited; // 지훈쓰 방문 체크
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node {
        int i;
        int j;
        boolean isFire; // 불인지 아닌지

        public Node(int i, int j, boolean isFire) {
            this.i = i;
            this.j = j;
            this.isFire = isFire;
        }
    }

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static int solve(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 불인 경우
                if (node.isFire) {
                    // 범위 벗어나면 패쓰
                    if (!isIn(newI, newJ)) continue;
                    // 벽이면 패쓰
                    if (arr[newI][newJ] == 1) continue;

                    arr[newI][newJ] = 1;
                    queue.add(new Node(newI, newJ, true));
                }
                // 지훈쓰인 경우
                else {
                    // 범위 벗어나면 -> 탈출 성공!
                    if (!isIn(newI, newJ)) return visited[node.i][node.j];
                    // 벽이면 패쓰
                    if (arr[newI][newJ] == 1) continue;
                    // 이미 방문했으면 패쓰
                    if (visited[newI][newJ] != 0) continue;

                    visited[newI][newJ] = visited[node.i][node.j] + 1;
                    queue.add(new Node(newI, newJ, false));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4179.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new int[N][M];

        // 입력 받기
        Node jihoon = null; // 지훈쓰 위치
        Queue<Node> queue = new LinkedList();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                // 벽
                if (c == '#') arr[i][j] = 1;
                // 지훈
                else if (c == 'J') {
                    jihoon = new Node(i, j, false);
                    visited[i][j] = 1;
                }
                // 불
                else if (c == 'F') {
                    // 불 위치 먼저 넣어주고
                    queue.add(new Node(i, j, true));
                    arr[i][j] = 1;
                }
            }
        }

        // 마지막에 지훈쓰 추가
        queue.add(jihoon);
        int result = solve(queue);

        if (result == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(result);
    }
}
