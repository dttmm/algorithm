package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 처음에는 구현인줄 알았음
 조건에 따라서 발을 이동하는 경우를 달리 하려고 했는데
 미래에 어떤 입력이 주어지냐에 따라 미리 어떤 발을 이동할지 정해야돼서
 조건을 따질 수가 없더라

 그럼 각각 한번씩 진행할 때마다의 최소값을 구하려고 했음
 근데 어느 순간에 최소값이 나왔다고 해서 계속 그 최소값이 최소값으로 유지되는 것이 아님
 어느 순간에는 최대값이라도 계속 진행하다보면 그 최대값이 최소값이 될 수도 있는 거임

 그래서 각각 한번씩 진행할 때마다
 발의 위치에 따른 최소값을 구해줌
 발이 있을 수 있는 위치는 5가지 * 5가지 = 25로
 매턴마다 최대 25가지의 경우의 수가 나올 수 있음
 그래서 최대 25 * 100,000 = 2,500,000가지의 경우의 수가 나옴
 매턴마다 발의 위치에 따른 최소값을 3차원 visited에 담아줌
 visited의 크기는 5 * 5 * 100,000 * 4바이트 = 천만 바이트 ~= 10MB로 메모리는 넉넉함

 근데 queue에서 메모리가 터졌음
 만약 최소값을 만족하는 경우가 queue에 나중에 들어갔다면
 먼저 들어갔던 최소값이 아닌 경우에 의해 불필요한 solve연산을 수행하게 되면서
 queue에 불필요한 값이 들어가면서 메모리 낭비하게 됨
 그래서 solve연산을 하기 전에
 최소값을 만족하는 경우인지 한번 확인해줌

 처음에 입력을 리스트에 다 받아두고 해도 되지만
 초기 불필요한 작업을 조금이라도 줄이기 위해
 입력 한번만 받으면서 수행하려고 노력함
 */
public class Main2342 {

    static int[][][] visited;
    static Queue<Node> queue;

    private static class Node {
        int right;  // 오른발 위치
        int left;   // 왼발 위치
        int power;  // 지금 까지 사용한 힘
        int k;      // 깊이

        public Node(int right, int left, int power, int k) {
            this.right = right;
            this.left = left;
            this.power = power;
            this.k = k;
        }
    }

    // next로 이동할 때 드는 힘 리턴
    static int needPower(int pre, int next) {
        // 중앙에서 이동하는 경우
        if (pre == 0) return 2;
        // 제자리인 경우
        if (pre == next) return 1;
        // 반대편인 경우
        if (Math.abs(pre - next) == 2) return 4;
        else return 3;
    }

    // 해당 칸(n)으로 오른발, 왼발 이동해봄
    static void solve(Node node, int n) {

        // 처음인 경우에는 오른발 이동하고 끝냄
        if (node.k == 0) {
            queue.add(new Node(n, 0, 2, node.k + 1));
            visited[n][0][node.k + 1] = 2;
            return;
        }

        // 오른발 이동
        // 이미 해당 자리에 왼발이 있는 경우는 패쓰
        if (n != node.left) {
            // 지금까지 사용한 힘 + 이동하는 데 드는 힘
            int newPower = node.power + needPower(node.right, n);
            // 새로 이동한 칸
            int nextVisited = visited[n][node.left][node.k + 1];

            // 해당 칸에 이동한 적이 없거나 힘이 더 적게 드는 경우
            // 이동하고 힘 갱신
            if (nextVisited == 0 || newPower < nextVisited) {
                visited[n][node.left][node.k + 1] = newPower;
                queue.add(new Node(n, node.left, newPower, node.k + 1));
            }
        }

        // 왼발 이동
        if (n != node.right) {
            int newPower = node.power + needPower(node.left, n);
            int nextVisited = visited[node.right][n][node.k + 1];

            if (nextVisited == 0 || newPower < nextVisited) {
                visited[node.right][n][node.k + 1] = newPower;
                queue.add(new Node(node.right, n, newPower, node.k + 1));
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2342.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        visited = new int[5][5][100001];
        queue = new LinkedList();
        queue.add(new Node(0, 0, 0, 0));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int k = 0;
        while (n != 0) {
            // 깊이가 같은 노드들 검사
            while (!queue.isEmpty() && queue.peek().k == k) {
                Node node = queue.poll();

                // 해당 노드가 최소값이 아닌 경우는 패쓰
                if (visited[node.right][node.left][node.k] < node.power) continue;
                solve(node, n);
            }

            n = Integer.parseInt(st.nextToken());
            k++;
        }

        // 깊이 k까지 오는 경우 중에서 최소값 찾기
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= 4; i++) {
            for (int j = 0; j <= 4; j++) {
                if (visited[i][j][k] == 0) continue;
                min = Math.min(min, visited[i][j][k]);
            }
        }

        System.out.println(min);
    }
}
