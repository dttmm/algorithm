package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 완탐으로 풀었음
 최적화할 방법이 딱히 보이지는 않았고
 모든 경우의 수를 따져봄
 N자체가 작고 시간제한도 2초라
 완탐 돌려도 충분할 것으로 판단

 0,0 부터 시작해서
 총 3가지 경우로 재귀 돌림
 1. 내 칸만 합치는 경우
 2. 내 아래쪽에 있는 놈들하고 합치는 경우
 3. 내 오른쪽에 있는 놈들하고 합치는 경우

 처음에는 이미 방문한 곳을 재귀 돌렸을 경우, return처리 했었는데
 그럼 아직 계산을 안한 칸들이 생기는 예외가 발생해서
 이미 방문한 곳을 재귀 돌렸을 경우, 다음 칸을 재귀 돌리도록 작업함

 아이디어 구현하기 빡세네
 */
public class Main14391 {

    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int max;

    static void solve(int index, int sum) {
        // 최대값 찾기
        max = Math.max(max, sum);

        // 범위 벗어난 경우 패쓰
        if (index >= N * M) return;

        // index -> 행, 열 값으로 전환
        int i = index / M;
        int j = index % M;

        // 이미 방문한 곳이면 다음 칸 검사
        if (visited[i][j]) {
            solve(index + 1, sum);
            return;
        }

        // 현재 칸 방문 처리
        visited[i][j] = true;

        // 1. 내 칸만 합치는 경우
        solve(index + 1, sum + arr[i][j]);

        // 2. 내 아래쪽에 있는 놈들하고 합치는 경우
        int newI = i + 1;
        int newSum = arr[i][j];
        // 연속으로 계속 갈 수 있을 때까지 가서 재귀
        while (newI < N && !visited[newI][j]) {
            visited[newI][j] = true;
            newSum = newSum * 10 + arr[newI][j];
            // 숫자 합치고 다음 칸 검사
            solve(index + 1, sum + newSum);
            newI++;
        }
        // 끝나면 위에서 했던 방문 처리 다시 초기화
        while (newI > i + 1) {
            visited[newI - 1][j] = false;
            newI--;
        }

        // 3. 내 오른쪽에 있는 놈들하고 합치는 경우
        int newJ = j + 1;
        newSum = arr[i][j];
        while (newJ < M && !visited[i][newJ]) {
            visited[i][newJ] = true;
            newSum = newSum * 10 + arr[i][newJ];
            solve(index + 1, sum + newSum);
            newJ++;
        }
        // 끝나면 위에서 했던 방문 처리 다시 초기화
        while (newJ > j + 1) {
            visited[i][newJ - 1] = false;
            newJ--;
        }

        // 현재 칸 방문 처리 초기화
        visited[i][j] = false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/14391.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];
        max = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                int n = s.charAt(j) - '0';
                arr[i][j] = n;
            }
        }

        solve(0, 0);
        System.out.println(max);
    }
}
