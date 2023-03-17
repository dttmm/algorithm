package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 어제 못푼거 리트라이!!
 처음에는 queue로 bfs돌리면서 벽이 나오면
 벽 정보를 리스트에 담고
 bfs 끝나면 리스트에 있는 벽들 부셔서 queue에 넣어주고
 벽 부신 count하나 올려주고
 다시 queue로 bfs 돌려줌
 중간에 newI, newJ가 N-1, M-1인 경우 return 시켜줌

 근데 벽 정보를 리스트에 꾸역꾸역 넣기만하고
 부신 벽 정보가 리스트에 계속 남아있어서 메모리 터짐

 그래서 리스트대신 queue를 이용해서 벽 정보 담음
 근데 이번엔 시간 초과

 리스트 도는게 문제인가 싶어서
 Node에 벽 부신 개수 count 변수 하나 추가하고
 queue대신 pq로 bfs돌림

 count가 낮은 놈부터 bfs돌리다가
 newI, newJ가 N-1, M-1인 경우 return 시켜줌
 근데도 시간초과남

 알고보니
 N, M이 둘다 1인 경우를 고려하지 못해서
 while(ture)에서 무한반복 일어났던거임
 그래서 i, j가 N-1, M-1인 경우 return 시켜줌

 pq 사용했을 때:     메모리 12380KB, 시간 104ms
 리스트 사용했을 때:  메모리 12500KB, 시간 100ms
 pq, newI, newJ에서도 N-1, M-1 체크했을 때:      메모리 12364KB, 시간 104ms
 리스트, newI, newJ에서도 N-1, M-1 체크했을 때:   메모리 12548KB, 시간 96ms

 pq에서 계속 정렬하는 것보다는 리스트 계속 도는게 더 빠르군하
 newI, newJ에서도 N-1, M-1 체크하니까 메모리 조금 더 사용하는군하
 */
public class Main1261 {

    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static PriorityQueue<Node> pq;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node implements Comparable<Node> {
        int i;
        int j;
        int count;

        public Node(int i, int j, int count) {
            this.i = i;
            this.j = j;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    static int solve() {
        pq.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int i = node.i;
            int j = node.j;

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                if (newI == N - 1 && newJ == M - 1) return node.count;

                if (!isIn(newI, newJ)) continue;
                if (visited[newI][newJ]) continue;

                // 벽인 경우
                if (arr[newI][newJ] == 1) {
                    pq.add(new Node(newI, newJ, node.count + 1));
                } else {
                    pq.add(new Node(newI, newJ, node.count));
                }

                visited[newI][newJ] = true;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1261.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int n = s.charAt(j) - '0';
                arr[i][j] = n;
            }
        }

        int answer = solve();
        System.out.println(answer);
    }
}
