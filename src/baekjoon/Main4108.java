package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 8방향 탐색문제
 완탐하면서 주위에 지뢰가 있는 경우 count 해줌
 지뢰가 있는 칸인 경우 count 배열에서 -1로 지뢰라고 표시해줌
 */
public class Main4108 {

    static int R;
    static int C;
    static char[][] arr;
    static int[][] count;
    static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
    static StringBuilder sb;

    static boolean isIn(int i, int j) {
        if (i >= 0 && i < R && j >= 0 && j < C) return true;
        return false;
    }

    // 지뢰 8방향 검사
    static void solve() {
        count = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 해당 칸이 지뢰인 경우 패쓰
                if (arr[i][j] == '*') {
                    count[i][j] = -1;
                    continue;
                }

                // 8방향 검사
                for (int dir = 0; dir < 8; dir++) {
                    int newI = i + di[dir];
                    int newJ = j + dj[dir];

                    // 범위 벗어나면 패쓰
                    if (!isIn(newI, newJ)) continue;
                    // 주위에 지뢰가 없다면 패쓰
                    if (arr[newI][newJ] == '.') continue;

                    count[i][j]++;
                }
            }
        }
    }

    // 지뢰 정보 출력
    static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 지뢰 있는 칸인 경우
                if (count[i][j] == -1) {
                    sb.append('*');
                    continue;
                }

                sb.append(count[i][j]);
            }
            sb.append("\n");
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4108.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while (R != 0) {
            arr = new char[R][C];
            for (int i = 0; i < R; i++) {
                String s = br.readLine();
                for (int j = 0; j < C; j++) {
                    char c = s.charAt(j);
                    arr[i][j] = c;
                }
            }

            solve();

            print();

            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }

        System.out.println(sb);
    }
}
