package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 bfs로 3가지 경우를 돌려주면 됨
 3번째 경우로 -1을 계속해서 목표에 도달할 경우도 고려해서
 범위를 최대의 2배로 설정함

 처음에는 visited배열을 일차원으로 관리했지만
 복사를 해야하는 경우 이미 같은 조건에서 복사를 한 적이 없는 경우에만 복사를 진행해야 하는데
 그 부분을 걸러낼 수가 없어씅ㅁ

 만약 현재 이모티콘의 길이가 2인데
 복사하기 전에 visited조건을 걸어놨다면
 이미 2는 방문 했으므로 복사가 진행이 안됨
 그렇다고해서
 복사하기 전에 visited조건 검사하지 않는다면
 2뿐만 아니라 모든 수에 대해서 무한 복사가 일어남

 그래서 visited배열을 2차원 배열로 관리함
 i행은 현재 이모티콘의 길이
 j열은 현재 클립보드의 길이

 은근 안풀려서 당황
 설계 철처히..
 */
public class Main14226 {

    static int S;
    static int[][] visited;
    static int answer;

    private static class Node {
        int cur;
        int clipBoard;

        public Node(int cur, int clipBoard) {
            this.cur = cur;
            this.clipBoard = clipBoard;
        }
    }

    static void solve() {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(1, 0));
        visited[1][0] = 1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int cur = node.cur;
            int clipBoard = node.clipBoard;

            if (cur == S) {
                answer = visited[cur][clipBoard];
                return;
            }

            // 붙여넣기
            if (node.clipBoard != 0) {
                if (cur + clipBoard < 2 * S && visited[cur + clipBoard][clipBoard] == 0) {
                    queue.add(new Node(cur + clipBoard, clipBoard));
                    visited[cur + clipBoard][clipBoard] = visited[cur][clipBoard] + 1;
                }
            }

            // 복사하기
            if (visited[cur][cur] == 0) {
                queue.add(new Node(cur, cur));
                visited[cur][cur] = visited[cur][clipBoard] + 1;
            }

            // 하나 삭제하기
            if (cur - 1 >= 0 && visited[cur - 1][clipBoard] == 0) {
                queue.add(new Node(cur - 1, clipBoard));
                visited[cur - 1][clipBoard] = visited[cur][clipBoard] + 1;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14226.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        visited = new int[S * 2][S * 2];
        answer = 0;

        solve();
        System.out.println(answer - 1);
    }
}
