package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 더러운 시뮬레이션 문제
 각 구슬을 Node로 만들어서 위치 정보 갖고 있게함

 구슬을 움직일 때 고려할 사항
 1. 앞에 있는 구슬을 먼저 움직여야됨
 왼쪽으로 움직이는 경우 더 왼쪽에 있는 구슬 먼저 움직이고
 그 다음 구슬을 움직여야됨
 각 방향마다 더 앞에 있는 구슬을 리턴하는 getFirst 함수,
 더 뒤에 있는 구슬을 리턴하는 getSecond 함수 만들어서 해결

 2. 빨간구슬이 구멍에 빠진다음 파란구슬이 구멍에 빠진 경우
 빨간구슬이 구멍에 들어갔다고 해서 정답 처리를 하면 안됨
 파란구슬까지 움직여보고 파란구슬이 구멍에 빠지면 실패이므로 이 경우 고려해야됨
 구슬을 움직일 때 빨간 구슬이 들어갔는지 체크하는 flag하나 만들어서 해결

 3. 앞에 있는 구슬이 멈춘 경우
 앞에 있는 구슬을 움직이고 벽에 막혀서 멈췄다면
 해당 자리는 구슬이 자리를 차지하고 있어서
 뒤에 있는 구슬이 해당 자리를 넘어가면 안됨
 앞에 있는 구슬이 멈추면 해당 자리에 '#'벽을 넣어서 해결
 뒤에 있는 구슬이 넘어 오지 못하게 함
 탐색이 끝나면 넣었던 벽 다시 복구 해줌

 4. 똑같은 위치에 있는 경우
 move를 했는데 두 구슬의 위치 변함이 없다면
 또 다시 move를 할 필요가 없으므로
 비용을 줄이기 위해 isSame 함수 만들어서 해결

 처음에는 재귀함수로 했었는데
 최소값을 찾기까지 시간이 오래걸려서 bfs로 갈아탐

 처음에는 4방향 모두 일일이 분기처리 해줬는데
 너무 코드가 더러워서 dir로 리팩토링함
 */
public class Main13460 {

    static int N;
    static int M;
    static char[][] arr;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node {
        char type;
        int i;
        int j;

        public Node(char type, int i, int j) {
            this.type = type;
            this.i = i;
            this.j = j;
        }
    }

    private static class NodeManager {
        static Node copy(Node o) {
            return new Node(o.type, o.i, o.j);
        }

        // 더 앞쪽에 있는 구슬 리턴
        static Node getFirst(int dir, Node o1, Node o2) {

            // 위쪽 (i 작은거)
            if (dir == 0) {
                if (o1.i <= o2.i) return o1;
                return o2;
            }

            // 오른쪽 (j 큰거)
            if (dir == 1) {
                if (o1.j >= o2.j) return o1;
                return o2;
            }

            // 왼쪽 (j 작은거)
            if (dir == 2) {
                if (o1.j <= o2.j) return o1;
                return o2;
            }

            // 아래쪽 (i 큰거)
            if (o1.i >= o2.i) return o1;
            return o2;
        }

        // 더 뒤쪽에 있는 구슬 리턴
        static Node getSecond(int dir, Node o1, Node o2) {

            // 위쪽 (i 큰거)
            if (dir == 0) {
                if (o1.i > o2.i) return o1;
                return o2;
            }

            // 오른쪽 (j 작은거)
            if (dir == 1) {
                if (o1.j < o2.j) return o1;
                return o2;
            }

            // 왼쪽 (j 큰거)
            if (dir == 2) {
                if (o1.j > o2.j) return o1;
                return o2;
            }

            // 아래쪽 (i 작은거)
            if (o1.i < o2.i) return o1;
            return o2;
        }
    }

    // 구멍에 빠졌는지 확인 1: 빨간구슬 빠짐 -1: 파란구슬 빠짐
    static int check(Node o) {
        if (arr[o.i][o.j] == 'O') {
            if (o.type == 'R') return 1;
            else return -1;
        }
        return 0;
    }

    // 구슬 이동
    static int move(int dir, Node r, Node b) {
        Node node1 = NodeManager.getFirst(dir, r, b);   // 더 앞에 있는 구슬
        Node node2 = NodeManager.getSecond(dir, r, b);  // 더 뒤에 있는 구슬
        boolean flag = false;   // 빨간 구슬 빠졌는지 플래그

        // 앞에 있는 구슬부터 이동
        while (arr[node1.i][node1.j] == '.') {
            node1.i = node1.i + di[dir];
            node1.j = node1.j + dj[dir];

            // 구멍에 빠졌는지 체크
            int result = check(node1);

            // 파란 구슬이 빠진 경우 -> 실패
            if (result == -1) return -1;

            // 빨간 구슬이 빠진 경우
            else if (result == 1) {
                flag = true;
                break;
            }
        }
        // 한 칸 뒤로 백도
        node1.i = node1.i + di[3 - dir];
        node1.j = node1.j + dj[3 - dir];
        // 앞에 있는 구슬이 구멍에 빠지지 않은 경우 -> 해당 자리 차지
        if (!flag) arr[node1.i][node1.j] = '#';

        // 뒤에 있는 구슬 이동
        while (arr[node2.i][node2.j] == '.') {
            node2.i = node2.i + di[dir];
            node2.j = node2.j + dj[dir];

            // 구멍에 빠졌는지 체크
            int result = check(node2);

            // 빨간 구슬이나 파란 구슬이 빠진 경우
            if (result != 0) {
                // 앞에 있는 구슬이 구멍에 빠지지 않은 경우 -> 앞에 있던 구슬이 차지하고 있던 자리 복구
                if (!flag) arr[node1.i][node1.j] = '.';
                return result;
            }
        }
        // 한 칸 뒤로 백도
        node2.i = node2.i + di[3 - dir];
        node2.j = node2.j + dj[3 - dir];
        // 앞에 있는 구슬이 구멍에 빠지지 않은 경우 -> 앞에 있던 구슬이 차지하고 있던 자리 복구
        if (!flag) arr[node1.i][node1.j] = '.';

        // 앞에 있던 빨간 구슬이 구멍에 빠지고 뒤에 있던 파란 구슬은 안 빠진 경우
        if (flag) return 1;
        return 0;
    }

    // 구슬이 이동한 후, 위치가 변했는지 체크
    static boolean isSame(Node r, Node b, Node newR, Node newB) {
        if (newR.i == r.i && newR.j == r.j && newB.i == b.i && newB.j == b.j) return true;
        return false;
    }

    static int solve(Node r, Node b) {
        Queue<Integer> queue_k = new LinkedList();
        Queue<Node> queue_r = new LinkedList();
        Queue<Node> queue_b = new LinkedList();
        queue_k.add(0);
        queue_r.add(r);
        queue_b.add(b);

        while (!queue_k.isEmpty()) {
            int k = queue_k.poll();
            r = queue_r.poll();
            b = queue_b.poll();

            // 10번 넘으면 실패
            if (k == 10) return -1;

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                Node newR = NodeManager.copy(r);
                Node newB = NodeManager.copy(b);
                int result = move(dir, newR, newB);

                // 빨간 구슬 빠짐
                if (result == 1) return k + 1;
                else if (result == 0) {
                    // 구슬의 위치가 변했다면
                    if (!isSame(r, b, newR, newB)) {
                        queue_k.add(k + 1);
                        queue_r.add(newR);
                        queue_b.add(newB);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13460.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];

        Node r = null;
        Node b = null;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;

                if (c == 'R') {
                    r = new Node('R', i, j);
                    arr[i][j] = '.';
                } else if (c == 'B') {
                    b = new Node('B', i, j);
                    arr[i][j] = '.';
                }
            }
        }

        System.out.println(solve(r, b));
    }
}
