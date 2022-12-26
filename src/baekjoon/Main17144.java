package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 단순 빡구현 문제
 미세먼지 확산의 경우
 arr을 전체 탐색하면서
 미세먼지가 확산할 수 있는 경우
 확산된 미세먼지를 brr에 담아 놓고
 arr 전체 탐색이 끝나면
 arr에 brr의 값을 더함

 공기청정기 가동의 경우
 한바퀴 순환하는 과정이 좀 노가다 였음
 위쪽 지역과 아래쪽 지역을 나눠서
 테두리에 있는 배열만 한칸씩 이동하도록 함
 이 과정에서 i와 j의 범위를 생각하는게 까다로운 포인트
 공기청정기 오른쪽은 새 바람이니까
 마지막에 공기청정기 오른쪽은 0으로 세팅해줌

 최종적으로 미세먼지 합 구할 때
 arr배열 전체 다 더함
 공기청정기도 -2만큼 더해졌으니까
 마지막에 +2해줌
 */
public class Main17144 {

    static int R;
    static int C;
    static int T;
    static int[][] arr;
    static int[] cleaner_i; // 0: 위쪽, 1: 아래쪽
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < R && j >= 0 && j < C) return true;
        return false;
    }

    // 확산된 미세먼지와 본래 미세먼지 합치기
    static void sumDust(int[][] brr) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += brr[i][j];
            }
        }
    }

    // 미세먼지 확산
    static void spread() {
        int[][] brr = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 미세먼지가 없거나 공기청정기인 경우 패쓰
                if (arr[i][j] <= 0) continue;

                // 확산시킬 양
                int amount = arr[i][j] / 5;
                // 몇번 확산 시켰는지 카운트
                int count = 0;

                // 4방향 탐색 시작
                for (int dir = 0; dir < 4; dir++) {
                    int newI = i + di[dir];
                    int newJ = j + dj[dir];

                    // 범위를 벗어난 경우 패쓰
                    if (!isIn(newI, newJ)) continue;
                    // 공기청정기인 경우 패쓰
                    if (arr[newI][newJ] == -1) continue;

                    // 확산 시킴
                    brr[newI][newJ] += amount;
                    count++;
                }
                // 확산한 양만큼 본래 위치에서 차감
                arr[i][j] -= amount * count;
            }
        }
        // 확산된 미세먼지와 본래 미세먼지 합치기
        sumDust(brr);
    }

    // 공기청정기 작동
    static void workCleaner() {
        // 위쪽 지역 순환
        for (int i = cleaner_i[0] - 1; i > 0; i--) {
            arr[i][0] = arr[i - 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            arr[0][j] = arr[0][j + 1];
        }
        for (int i = 0; i < cleaner_i[0]; i++) {
            arr[i][C - 1] = arr[i + 1][C - 1];
        }
        for (int j = C - 1; j > 1; j--) {
            arr[cleaner_i[0]][j] = arr[cleaner_i[0]][j - 1];
        }

        // 아래쪽 지역 순환
        for (int i = cleaner_i[1] + 1; i < R - 1; i++) {
            arr[i][0] = arr[i + 1][0];
        }
        for (int j = 0; j < C - 1; j++) {
            arr[R - 1][j] = arr[R - 1][j + 1];
        }
        for (int i = R - 1; i > cleaner_i[1]; i--) {
            arr[i][C - 1] = arr[i - 1][C - 1];
        }
        for (int j = C - 1; j > 1; j--) {
            arr[cleaner_i[1]][j] = arr[cleaner_i[1]][j - 1];
        }

        // 공기청정기 오른쪽은 0으로 세팅
        arr[cleaner_i[0]][1] = 0;
        arr[cleaner_i[1]][1] = 0;
    }

    // 전체 미세먼지 합 반환
    static int totalDust() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += arr[i][j];
            }
        }

        // 공기청정기 뺀것만큼 다시 더해줌
        return sum + 2;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17144.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        cleaner_i = new int[2];
        int clear_flag = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int n = Integer.parseInt(st.nextToken());

                // 공기청정기 i행 위치 저장
                if (n == -1) {
                    cleaner_i[clear_flag] = i;
                    clear_flag++;
                }

                arr[i][j] = n;
            }
        }

        for (int t = 0; t < T; t++) {
            spread();
            workCleaner();
        }

        System.out.println(totalDust());
    }
}
