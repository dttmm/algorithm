package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 7분 구현 20분
 구현
 3차원 배열 탐색 문제
 그냥 각 지점에서 26방향 탐색하여
 지뢰 개수 찾아주면 됨

 아 int to char 변환하는 과정 헷갈려서 헤멤
 (char)1을 하면 49가 아니라 그대로 1로 변하는구나
 */
public class Main29733 {

    static int N;
    static int M;
    static int H;
    static char[][][] arr;
    static int[] di = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dj = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    // 범위 체크
    static boolean isIn(int i, int j, int h) {
        if (i >= 0 && i < N && j >= 0 && j < M && h >= 0 && h < H) return true;
        return false;
    }

    // 26방향 탐색
    static int solve(int i, int j, int h) {
        int count = 0;

        // 높이 탐색
        for (int hh = -1; hh < 2; hh++) {
            // 2차원 배열 탐색
            for (int dir = 0; dir < 9; dir++) {
                // 제자리인 경우
                if (hh == 0 && dir == 4) continue;

                int newI = i + di[dir];
                int newJ = j + dj[dir];
                int newH = h + hh;

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ, newH)) continue;
                // 지뢰 아니면 패쓰
                if (arr[newI][newJ][newH] != '*') continue;

                // 지뢰 카운트
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29733.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new char[N][M][H];

        // 입력 받기
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = s.charAt(j);
                    arr[i][j][h] = c;
                }
            }
        }

        // 정답 출력 배열
        char[][][] brr = new char[N][M][H];
        // 3차원 배열 완탐
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 지뢰인 경우
                    if (arr[i][j][h] == '*') {
                        brr[i][j][h] = '*';
                        continue;
                    }

                    // 인접한 지뢰 개수 반환
                    int n = solve(i, j, h);
                    n %= 10;
                    brr[i][j][h] = (char) (n + '0');
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    sb.append(brr[i][j][h]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
