package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 구현 7분
 bfs
 기본적인 bfs문제라 설계없이 바로 풀음
 4방향 탐색하면서 bfs
 방문처리 따로 visited배열 만들지 않고
 arr배열 이용해서함
 */
public class Main21736 {

    static int N;
    static int M;
    static int[][] arr; // 0: 빈 곳, 1: 벽 or 이미 방문한 곳, 2: 사람 있는 곳
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // bfs
    static int solve(int start_i, int start_j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(start_i);
        queue_j.add(start_j);
        arr[start_i][start_j] = 1;

        int count = 0;  // 만난 사람의 수
        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나는 경우 패쓰
                if (!isIn(newI, newJ)) continue;
                // 벽 or 이미 방문한 경우 패쓰
                if (arr[newI][newJ] == 1) continue;

                // 사람이 있는 경우
                if (arr[newI][newJ] == 2) count++;
                arr[newI][newJ] = 1;
                queue_i.add(newI);
                queue_j.add(newJ);

            }
        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/21736.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        int start_i = 0;
        int start_j = 0;
        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);

                // 벽
                if (c == 'X') arr[i][j] = 1;
                // 사람
                else if (c == 'P') arr[i][j] = 2;
                // 도연
                else if (c == 'I') {
                    start_i = i;
                    start_j = j;
                }
            }
        }

        int result = solve(start_i, start_j);
        if (result == 0) System.out.println("TT");
        else System.out.println(result);
    }
}
