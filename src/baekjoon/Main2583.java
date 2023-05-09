package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 전형적인 bfs문제
 이차원 배열(arr) 하나 선언하고
 arr에 초기 영역 칠하고
 나머지 빈 공간에 대해서
 색 칠해가면서 bfs돌림

 아 입력 더럽다
 x y <-> i j
 서로 변환하는거 헷갈리네
 N M도 반대로 사용해서 더 헷갈렸네

 아 사소한 실수
 static 변수 선언해놓고
 지역변수로 새로 선언해서 입력 받았네
 이상한 데서 시간 쓰고 삽질했네
 끄앙
 */
public class Main2583 {

    static int N;
    static int M;
    static int K;
    static boolean[][] arr;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 벗어났는지 체크
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
        arr[start_i][start_j] = true;
        int count = 1;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            // 4방향 탐색
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 이미 방문했으면 패쓰
                if (arr[newI][newJ]) continue;

                queue_i.add(newI);
                queue_j.add(newJ);
                arr[newI][newJ] = true;
                count++;
            }
        }
        // 영역의 넓이 리턴
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2583.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 초기 입력 영역 표시해줌
            for (int x = x1; x < x2; x++) {
                for (int y = y1; y < y2; y++) {
                    // x축 y축 <-> i j 변환 헷갈리지 않게 조심
                    arr[y][x] = true;
                }
            }
        }

        // 이차원 배열 돌면서
        List<Integer> list = new ArrayList();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 이미 탐색한 영역이면 패쓰
                if (arr[i][j]) continue;

                // 영역의 넓이 받아서 추가
                int result = solve(i, j);
                list.add(result);
            }
        }

        StringBuilder sb = new StringBuilder(list.size() + "\n");
        Collections.sort(list);
        for (int i : list) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}
