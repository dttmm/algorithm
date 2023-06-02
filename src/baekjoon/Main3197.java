package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 35분 구현 30분
 빡구현 + bfs + 유니온 파인드

 bfs 자체는 쉬운데
 최대 관건은 두 백조가 연결되었는지를 어떻게 판단할 것인가
 두 지점이 연결되었는지 판단한다?? -> 유니온파인드 생각

 bfs를 통해 그룹을 생성하고
 bfs로 빙하를 녹이면서
 그룹을 합쳐가면서
 백조가 있는 그룹이 같은 부모인지 확인해줌
 */
public class Main3197 {

    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;     // 해당 위치 방문했는지 여부
    static Queue<Node> meltQueue;   // 녹일 빙하 저장
    static Node[] target;           // 백조 있는 위치
    static int[] p;                 // 각 group의 부모
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    private static class Node {
        int i;
        int j;
        int group;  // 그룹 정보

        public Node(int i, int j, int group) {
            this.i = i;
            this.j = j;
            this.group = group;
        }
    }

    // 범위 검사
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 연결 가능한 땅은 연결해주고 녹일 빙하는 녹일 큐에 추가
    static void solve(int i, int j, int group) {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(i, j, group));
        visited[i][j] = true;
        arr[i][j] = group;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;

                visited[newI][newJ] = true;
                // 땅인 경우 -> 같은 그룹으로 묶음
                if (arr[newI][newJ] == 0) {
                    queue.add(new Node(newI, newJ, group));
                    arr[newI][newJ] = group;
                }
                // 빙하인 경우 -> 녹일 큐에 추가
                else if (arr[newI][newJ] == -1) {
                    meltQueue.add(new Node(newI, newJ, group));
                }
            }
        }
    }

    // 부모 반환
    static int getP(int x) {
        if (p[x] == x) return x;
        p[x] = getP(p[x]);
        return p[x];
    }

    // 부모 같은지 검사
    static boolean isSameP(int i1, int j1, int i2, int j2) {
        int group1 = arr[i1][j1];
        int group2 = arr[i2][j2];

        int p1 = getP(group1);
        int p2 = getP(group2);

        return p1 == p2;
    }

    // 부모 합침
    static void unionP(int i1, int j1, int i2, int j2) {
        int group1 = arr[i1][j1];
        int group2 = arr[i2][j2];

        int p1 = getP(group1);
        int p2 = getP(group2);

        if (p1 <= p2) p[p2] = p1;
        else p[p1] = p2;
    }

    // 빙하 녹이기
    static int melt() {
        int count = 0;
        while (!meltQueue.isEmpty()) {
            Node node = meltQueue.poll();

            // 하루 돌았는지 검사
            if (node.i == -1) {
                // 백조의 부모가 같은 경우 -> 정답 찾음!!
                if (isSameP(target[0].i, target[0].j, target[1].i, target[1].j)) return count;

                meltQueue.add(node);
                count++;
                continue;
            }

            arr[node.i][node.j] = node.group;

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;

                // 땅인 경우
                if (arr[newI][newJ] != -1) {
                    // 그룹 부모 같은지 검사
                    if (isSameP(node.i, node.j, newI, newJ)) continue;
                    // 부모 합침
                    unionP(node.i, node.j, newI, newJ);
                }

                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;
                visited[newI][newJ] = true;
                meltQueue.add(new Node(newI, newJ, node.group));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3197.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        meltQueue = new LinkedList();
        target = new Node[2];

        // 입력 받기
        int index = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                // 빙하
                if (c == 'X') {
                    arr[i][j] = -1;
                    continue;
                }

                // 백조
                if (c == 'L') {
                    target[index] = new Node(i, j, 0);
                    index++;
                }
            }
        }

        // 연결 가능한 땅은 연결해주고 녹일 빙하는 녹일 큐에 추가
        meltQueue.add(new Node(-1, -1, -1));
        int groupNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 빙하 || 이미 연결된 땅인 경우 패쓰
                if (arr[i][j] != 0) continue;

                solve(i, j, groupNum);
                groupNum++;
            }
        }

        // 각 그룹의 부모 자신으로 설정
        p = new int[groupNum];
        for (int i = 1; i < groupNum; i++) {
            p[i] = i;
        }

        // 녹임
        int answer = melt();
        System.out.println(answer);
    }
}
