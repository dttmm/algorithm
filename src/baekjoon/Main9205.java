package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 완탐으로 풀었음
 시작점부터 시작해하면 전체 좌표를 돌면서
 해당 점에서 갈 수 있는 곳(1000미터 이내)을 찾아서 큐에 넣고
 큐를 계속 돌림
 돌리다가 목적지 나오면 정답!!

 일단 N이 100이니까 완탐을 돌려서
 100*100 = 10,000 이고
 테케도 50개니까 시간은 충분할 것이라 판단함

 군데 풀고나서 문제 유형을 보니까 그래프이길래
 다른 방법이 있난 풀이 검색해봤는데
 다들 처음에 완탐 돌면서 갈수 있는 곳끼리 연결 그래프를 만들고
 bfs를 돌리네.. 비효율적이게..
 그것도 죄다 복붙 풀이..
 */
public class Main9205 {

    static int N;
    static Node[] nodes;    // 모든 좌표 정보 저장
    static boolean[] visited;
    static Node destination;    // 목적지 좌표

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 두 좌표 사이가 1000미터 이내인지 판별
    public static boolean isIn(Node node1, Node node2) {
        if ((Math.abs(node1.x - node2.x) + Math.abs(node1.y - node2.y)) <= 1000) return true;
        return false;
    }

    public static boolean solve() {
        boolean flag = false;
        Queue<Node> queue = new LinkedList();
        queue.add(nodes[0]);
        visited[0] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 목적지 도착한 경우
            if (node == destination) {
                flag = true;
                break;
            }

            // 모든 좌표 돌면서 현재 좌표에서 해당 좌표로 갈 수 있는지 확인
            for (int i = 0; i < nodes.length; i++) {
                // 자기 자신 좌표는 패쓰
                if (nodes[i] == node) continue;
                // 이미 방문한 좌표 패쓰
                if (visited[i]) continue;
                // 범위 벗어난 좌표 패쓰
                if (!isIn(node, nodes[i])) continue;

                visited[i] = true;
                queue.add(nodes[i]);
            }
        }

        return flag;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9205.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            nodes = new Node[N + 2];
            visited = new boolean[N + 2];

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                Node newNode = new Node(x, y);
                nodes[i] = newNode;

                if (i == N + 1) destination = newNode;
            }

            boolean result = solve();
            if (result) bw.write("happy\n");
            else bw.write("sad\n");
        }

        bw.flush();
        bw.close();
    }
}
