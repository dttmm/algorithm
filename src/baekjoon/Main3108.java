package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 설계 9분 구현 10분 디버깅 10분
 bfs돌리면서 연결되어있는 직사각형들을 모두 탐색하면서
 탐색 횟수(count)를 세어줌
 배열을 사용하기 위해 입력되는 좌표값을 +500해줌
 그리고 꼭지점이 (0,0)에서 시작하면 count를 -1로 설정하여
 초기 탐색 횟수를 하나 줄임

 생각해보니 4방향 탐색으로 bfs를 돌리면
 서로 연결되어있지 않은 직사각형도 1칸 차이 옆에 있으면 탐색해 버리는 현상 발생
 그래서 입력 받은 좌표값을 *2배하여 거리를 늘려줌

 틀림
 생각해보니 x1,y1 이나 x2,y2가 (0,0)에서 시작하는 경우에만
 count를 -1로 설정하였는데 x1,y2과 x2,y1도 (0,0)일 수 있다는 것을 깨달음

 틀림
 생각해보니 꼭 꼭지점이 (0,0)에서 시작하지 않더라도
 변이 (0,0) 위에 있어도 처음에 연필을 올릴 필요가 없넹
 */
public class Main3108 {

    static int N;
    static boolean[][] arr; // 좌표 정보 & 방문 체크
    static int count;
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i <= 2000 && j >= 0 && j <= 2000) return true;
        return false;
    }

    // bfs
    static void solve(int start_i, int start_j) {
        Queue<Integer> queue_i = new LinkedList();
        Queue<Integer> queue_j = new LinkedList();
        queue_i.add(start_i);
        queue_j.add(start_j);
        arr[start_i][start_j] = false;

        while (!queue_i.isEmpty()) {
            int i = queue_i.poll();
            int j = queue_j.poll();

            for (int dir = 0; dir < 4; dir++) {
                int newI = i + di[dir];
                int newJ = j + dj[dir];

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 직사각형이 아니거나 이미 방문했으면 패쓰
                if (!arr[newI][newJ]) continue;

                arr[newI][newJ] = false;
                queue_i.add(newI);
                queue_j.add(newJ);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3108.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new boolean[2001][2001];
        count = 0;

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = (Integer.parseInt(st.nextToken()) + 500) * 2;
            int y1 = (Integer.parseInt(st.nextToken()) + 500) * 2;
            int x2 = (Integer.parseInt(st.nextToken()) + 500) * 2;
            int y2 = (Integer.parseInt(st.nextToken()) + 500) * 2;

            // 가로변 그리기
            for (int x = x1; x <= x2; x++) {
                // 변이 (0,0)을 지나는 경우
                if ((x == 1000 && y1 == 1000) || (x == 1000 && y2 == 1000)) count = -1;
                arr[x][y1] = true;
                arr[x][y2] = true;
            }

            // 세로변 그리기
            for (int y = y1; y <= y2; y++) {
                // 변이 (0,0)을 지나는 경우
                if ((x1 == 1000 && y == 1000) || (x2 == 1000 && y == 1000)) count = -1;
                arr[x1][y] = true;
                arr[x2][y] = true;
            }
        }

        // 직사각형 탐색
        for (int i = 0; i <= 2000; i++) {
            for (int j = 0; j <= 2000; j++) {
                if (!arr[i][j]) continue;

                solve(i, j);
                count++;
            }
        }

        System.out.println(count);
    }
}
