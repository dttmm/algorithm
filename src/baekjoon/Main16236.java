package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BFS를 계속 반복하며
 * 먹을 수 있는 먹이가 없는 경우 중지함
 * <p>
 * 처음에는 그냥 BFS를 큐로만 구현하였음
 * 4방향 탐색할 때, 순서를 상좌우하 순으로
 * 그러면 우선적으로 탐색하는 순위가 위에거 먼저 탐색하고
 * 그다음 왼쪽것을 먼저 탐색하게 되니까
 * 근데 그건 탐색 순서일 뿐이고
 * 거리가 가까울 떄의 우선순위가 위,왼쪽이 우선이 되는 것이 아니었음
 * 맨 윗줄에서 탐색할 경우 예외가 발생했었음
 * <p>
 * 그래서 그냥 Node클래스에 우선순위 설정한다음
 * BFS를 우선순위 큐로 구현하였음
 */
public class Main16236 {

    static int N;
    static int[][] arr;
    static Node shark;
    static int level;
    static int count;
    static int answer;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, -1, 1, 0};

    private static class Node implements Comparable<Node> {
        int i;
        int j;
        int time;

        public Node(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            if (this.time != o.time) return this.time - o.time;
            if (this.i != o.i) return this.i - o.i;
            return this.j - o.j;
        }
    }

    // 범위 벗어나지 않았는지 확인
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    // 물고기 먹기!!
    static void eat(Node node) {
        arr[node.i][node.j] = 0;
        count++;
        if (count == level) {
            level++;
            count = 0;
        }

        answer += node.time;

        // 상어 위치 해당 노드로 설정
        node.time = 0;
        shark = node;
    }

    static boolean bfs(Node start) {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Node> queue = new PriorityQueue();
        queue.add(start);
        visited[start.i][start.j] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 상어가 먹을 수 있는 물고기를 만난 경우
            if (arr[node.i][node.j] != 0 && arr[node.i][node.j] < level) {
                eat(node);
                return true;
            }

            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위를 벗어났으면 패쓰
                if (!isIn(newI, newJ)) continue;

                // 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                // 상어 크기보다 큰 물고기면 패쓰
                if (arr[newI][newJ] > level) continue;

                visited[newI][newJ] = true;
                queue.add(new Node(newI, newJ, node.time + 1));
            }
        }

        // 다 돌았는데 먹을 수 있는 녀석이 없는 경우
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16236.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        level = 2;
        count = 0;
        answer = 0;

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 9) {
                    shark = new Node(i, j, 0);
                } else {
                    arr[i][j] = n;
                }
            }
        }

        while (bfs(shark)) ;

        System.out.println(answer);
    }
}
