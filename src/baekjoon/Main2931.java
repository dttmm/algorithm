package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 설계 31분 구현 1시간 8분
 * 더러운 구현 문제
 * 처음에는 한 지점에서 길을 따라 가면서
 * 길이 끊겼을 때
 * 7가지 블록을 넣어보는 방식으로 풀려고 했으나
 * 비효율 + 복잡쓰
 * <p>
 * 그래서 그냥 간단하게
 * 두 지점에서부터 길을 따라 가면서
 * 길이 끊긴 곳에서
 * 각각 어느 방향에서 왔는지 분기 처리를 통해
 * 길이 끊긴 곳에 어떤 블록을 넣어야 하는지 결정함
 * 일단 +모양 빼고 나머지 모양을 먼저 넣은 다음
 * 나머지 모양을 넣을 때, 모든 경로를 탐색 했다면 해당 모양을 선택하고
 * 아직 탐색하지 못한 블록이 있다면,
 * 나머지 모양으로는 갈 수 없는 경로가 있다는 뜻이므로 +모양을 선택함
 * <p>
 * +모양을 먼저 선택하게 되면,
 * 나머지 모양만 사용해도 되는 경우가 있을 수 있고
 * 이상한 경로로 갈 수도 있는 예외가 발생할 수 있음
 * <p>
 * 노드를 한 칸씩 전진할 때마다 depth를 증가시키면서
 * 목표 지점에 도달했을 때,
 * 전체 블록 수(count) 만큼 depth가 발생했다면 정답을 찾은 것으로 판별함
 * 그런데 +모양의 경우, 해당 모양은 두번 지날 수 있으므로
 * 다른 모양보다 count수를 1더 증가시킴
 */
public class Main2931 {

    static int N;
    static int M;
    static int[][] arr;
    static int count;   // 블록 전체 개수(삽입할 블록 포함, +모양을 2만큼 차지)
    static char[] type = {0, '1', '2', '3', '4', '-', '|', '+'};    // 블록 모양
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static Node[] target;   // M, Z 위치

    private static class Node {
        int i;
        int j;
        int dir;
        int depth;

        public Node(int i, int j, int dir, int depth) {
            this.i = i;
            this.j = j;
            this.dir = dir;
            this.depth = depth;
        }
    }

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 현재 진행하고 있는 방향과 블록 모양에 따라 다음 진행 방향 구하기
    static int newDir(int i, int j, int curDir) {
        // - | + 모양 -> 그대로 전진하면 됨
        if (arr[i][j] >= 5) return curDir;

        if (arr[i][j] == 1) return (curDir + 1) % 4;
        if (arr[i][j] == 2) return (curDir + 2) % 4;
        if (arr[i][j] == 3) return (curDir + 3) % 4;
        if (arr[i][j] == 4) return (curDir + 2) % 4;

        return -1;
    }

    // 두 진입 방향에 따른 필요한 모양 결정
    static int getType(int dir1, int dir2) {
        if (dir1 == 0) {
            if (dir2 == 1) return 4;
            if (dir2 == 2) return 1;
        }
        if (dir1 == 1) {
            if (dir2 == 0) return 4;
            if (dir2 == 3) return 3;
        }
        if (dir1 == 2) {
            if (dir2 == 0) return 1;
            if (dir2 == 3) return 2;
        }
        if (dir1 == 3) {
            if (dir2 == 1) return 3;
            if (dir2 == 2) return 2;
        }
        return -1;
    }

    // 해킹된 블록 찾기
    static Node find(Node startNode) {
        Queue<Node> queue = new LinkedList();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 시작 지점인 경우
            if (node.dir == -1) {
                for (int dir = 0; dir < 4; dir++) {
                    int newI = node.i + di[dir];
                    int newJ = node.j + dj[dir];

                    if (!isIn(newI, newJ)) continue;
                    if (arr[newI][newJ] == 0) continue;

                    queue.add(new Node(newI, newJ, dir, 1));
                    break;
                }
                continue;
            }

            int newI = node.i;
            int newJ = node.j;

            int dir = newDir(newI, newJ, node.dir);
            newI += di[dir];
            newJ += dj[dir];

            // 해킹된 지점 찾은 경우
            if (arr[newI][newJ] == 0) return new Node(newI, newJ, dir, -1);

            queue.add(new Node(newI, newJ, dir, node.depth + 1));
        }

        return null;
    }

    // 해킹된 지점에 블록 넣고 경로 따라가기
    static boolean insert(int i, int j, int type) {
        arr[i][j] = type;
        Queue<Node> queue = new LinkedList();
        queue.add(target[0]);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 시작 지점인 경우
            if (node.dir == -1) {
                for (int dir = 0; dir < 4; dir++) {
                    int newI = node.i + di[dir];
                    int newJ = node.j + dj[dir];

                    if (!isIn(newI, newJ)) continue;
                    if (arr[newI][newJ] == 0) continue;

                    queue.add(new Node(newI, newJ, dir, 1));
                    break;
                }
                continue;
            }

            int newI = node.i;
            int newJ = node.j;

            int dir = newDir(newI, newJ, node.dir);
            newI += di[dir];
            newJ += dj[dir];

            // 목표 지점에 도달한 경우 -> 모든 블록을 지나왔는지 체크
            if (newI == target[1].i && newJ == target[1].j) return node.depth == count;

            queue.add(new Node(newI, newJ, dir, node.depth + 1));
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2931.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        count = -1; // .이 아닌 것의 개수 - M,Z 지점(2) + 해킹된 블록(1)
        target = new Node[2];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                if (c == '.') continue;

                if (c == '-') {
                    arr[i][j] = 5;
                } else if (c == '|') {
                    arr[i][j] = 6;
                } else if (c == '+') {
                    arr[i][j] = 7;
                    count++;    // +모양은 2번 지나갈 수 있으므로
                } else if (c == 'M') {
                    target[0] = new Node(i, j, -1, -1);
                } else if (c == 'Z') {
                    target[1] = new Node(i, j, -1, -1);
                } else {
                    arr[i][j] = c - '0';
                }
                count++;
            }
        }

        // 두 지접에서부터 해킹된 지점 찾기
        Node node1 = find(target[0]);
        Node node2 = find(target[1]);

        boolean result;
        int answer;
        // 서로 반대 방향인 경우
        if (node1.dir + node2.dir == 3) {
            if (node1.dir == 0 || node1.dir == 3) {
                answer = 6;
                result = insert(node1.i, node1.j, 6);
            } else {
                answer = 5;
                result = insert(node1.i, node1.j, 5);
            }
        }
        // 방향이 꺾인 경우
        else {
            int type = getType(node1.dir, node2.dir);
            answer = type;
            result = insert(node1.i, node1.j, type);
        }

        // +모양이 필요한 경우
        if (!result) answer = 7;

        String s = (node1.i + 1) + " " + (node1.j + 1) + " ";
        if (answer <= 4) System.out.println(s + answer);
        else if (answer == 5) System.out.println(s + '-');
        else if (answer == 6) System.out.println(s + '|');
        else System.out.println(s + '+');
    }
}
