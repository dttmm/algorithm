package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 7분 구현 31분
 bfs

 bfs를 수행하며
 문을 만났을 때는 해당 문의 열쇠가 있는지 확인한 후,
 열쇠가 있으면 문을 열고 진행하고
 욜쇠가 없으면 나중에 열쇠가 생기면 열도록 다른 큐(doorQueueList)에 해당 위치 저장함

 열쇠를 만났을 때는 열쇠를 획득하고
 해당 열쇠로 열 수 있는 문(doorQueueList)을 열기 위해
 열수 있는 문을 bfs 큐에 추가함

 bfs에서 4방향 탐색을 하고
 newI, newJ를 이용해서 위의 조건검사를 할 수도 있지만
 그러면
 bfs를 시작하려는 위치에서도 위의 조건검사를 동일하게 해줘야함
 그래서 그냥
 bfs를 시작할 위치를 bfs 큐에 넣고
 큐에서 해당 위치를 꺼낼 때
 조건 검사를 통해 4방향 탐색을 계속 진행해 나갈지 판단함

 지도의 테두리에서만 bfs를 시작하기 위해
 조건을 설정하는데 좀 헤맸음
 */
public class Main9328 {

    static int N;
    static int M;
    static char[][] arr;
    static boolean[][] visited;
    static Queue<Node>[] doorQueueList; // 나중에 열쇠를 획득하면 열 수 있는 문의 위치 저장
    static boolean[] keys;              // 획득한 열쇠 정보
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static int answer;

    // 위치 정보
    private static class Node {
        int i;
        int j;

        private Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static void solve(int start_i, int start_j) {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(start_i, start_j));
        visited[start_i][start_j] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            char c = arr[node.i][node.j];

            // 문 인경우
            if (c >= 'A' && c <= 'Z') {
                int index = c - 'A';

                // 열쇠가 없는 경우
                if (!keys[index]) {
                    doorQueueList[index].add(new Node(node.i, node.j));
                    continue;
                }
            }
            // 열쇠인 경우
            else if (c >= 'a' && c <= 'z') {
                int index = c - 'a';
                keys[index] = true;

                // 해당 열쇠로 열 수 있는 문 열고 드감
                while (!doorQueueList[index].isEmpty()) {
                    queue.add(doorQueueList[index].poll());
                }
            }
            // 문서인 경우
            else if (c == '$') {
                answer++;
            }

            // 4방향 탐색 진행
            for (int dir = 0; dir < 4; dir++) {
                int newI = node.i + di[dir];
                int newJ = node.j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 벽이면 패쓰
                if (arr[newI][newJ] == '*') continue;
                // 이미 방문했으면 패쓰
                if (visited[newI][newJ]) continue;
                visited[newI][newJ] = true;

                queue.add(new Node(newI, newJ));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9328.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new char[N][M];
            visited = new boolean[N][M];
            doorQueueList = new Queue[26];
            keys = new boolean[26];
            answer = 0;

            // 초기화
            for (int i = 0; i < 26; i++) {
                doorQueueList[i] = new LinkedList();
            }

            // 입력 받기
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);
                    arr[i][j] = c;
                }
            }

            // 열쇠 정보 입력 받기
            String key = br.readLine();
            if (!key.equals("0")) {
                for (int i = 0; i < key.length(); i++) {
                    int c = key.charAt(i);
                    keys[c - 'a'] = true;
                }
            }

            // 테두리에서 bfs 진행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 테두리 검사
                    if ((i != 0 && i != N - 1) && (j != 0 && j != M - 1)) continue;

                    char c = arr[i][j];
                    // 벽인 경우 패쓰
                    if (c == '*') continue;
                    // 이미 방문한 경우 패쓰
                    if (visited[i][j]) continue;

                    // 해당 위치에서 bfs 수행
                    solve(i, j);
                }
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb);
    }
}
