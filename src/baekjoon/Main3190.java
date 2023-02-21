package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 일단 뱀이 움직일 때, 꼬리가 없어지는 경우를
 큐로 관리하면 처리할 수 있을 거라 생각함

 그냥 뱀 한칸씩 이동하면서
 회전 할 수 있는지 체크하고
 사과 있는지 없는지 체크해서
 꼬리 없애야 하는지 마는지 판단함

 방향 전환한느 부분에서 좀 헤멤
 설계를 좀 더 꼼꼼하게 해야겠음

 회전 정보를 큐에 담았는데
 map에 담아서 관리하는 방법도 있었구나..
 */
public class Main3190 {

    static int N;
    static int K;
    static int L;
    static int[][] arr; // 사과표시 and 방문 표시 1: 사과, -1: 방문
    static Queue<Move> moves;   // 회전 정보 담음
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    // 위치 정보
    private static class Node {
        int i;
        int j;

        public Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // 회전 정보
    private static class Move {
        int time;
        char dir;

        public Move(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    public static boolean isIn(int i, int j) {
        if (i > 0 && i <= N && j > 0 && j <= N) return true;
        return false;
    }

    public static int solve() {
        int time = 0;
        int dir = 1;    // 초기 방향은 오른쪽

        Queue<Node> queue = new LinkedList();   // 현재 위치 담음
        Queue<Node> visited = new LinkedList(); // 현재 뱀 몸뚱아리 위치 담음

        Node startNode = new Node(1, 1);
        queue.add(startNode);
        visited.add(startNode);
        arr[1][1] = -1;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 회전할 것이 남아 있는 경우
            if (!moves.isEmpty()) {
                Move move = moves.peek();

                // 현재 시간과 회전할 시간 비교
                if (time == move.time) {
                    moves.poll();

                    // 왼쪽 회전
                    if (move.dir == 'L') {
                        dir = (dir + 3) % 4;
                    }
                    // 오른쪽 회전
                    else {
                        dir = (dir + 1) % 4;
                    }
                }
            }

            int newI = node.i + di[dir];
            int newJ = node.j + dj[dir];

            time++;

            // 범위 벗어난 경우
            if (!isIn(newI, newJ)) break;

            // 자기 몸통에 부딪힌 경우
            if (arr[newI][newJ] == -1) break;

            // 사과 없는 경우
            if (arr[newI][newJ] == 0) {
                // 제일 마지막 꼬리 정보 삭제
                Node vistedNode = visited.poll();
                arr[vistedNode.i][vistedNode.j] = 0;
            }

            Node newNode = new Node(newI, newJ);
            queue.add(newNode);
            visited.add(newNode);
            arr[newI][newJ] = -1;
        }

        return time;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3190.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        arr = new int[N + 1][N + 1];
        moves = new LinkedList();


        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            arr[i][j] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int k = 0; k < L; k++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            moves.add(new Move(time, dir));
        }

        System.out.println(solve());
    }
}
