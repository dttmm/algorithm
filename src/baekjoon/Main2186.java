package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 7분 구현 12분 디버깅 11분
 주어진 target의 첫 단어로 시작하는 곳에서 부터
 4방향 탐색을 통해
 글자를 완성시킬 수 있는 경우를 찾아감
 4방향 탐색 시, 최대 k번 전진할 수 있도록 반복문 추가함

 시간초과 나서 다시 풀음
 어떤 지점에서 depth번째 글자를 만들 수 있는 경우를 dp로 저장하여 중복을 없앰
 만들 수 있는 글자가 없는 경우 dp값이 0이 되기 때문에
 dp배열을 0으로 초기화한 경우,
 만들 수 있는 글자가 없다는 뜻인지 아직 방문하지 않았단 뜻인지 알 수 없으므로
 dp배열을 -1로 초기화 해줌
 */
public class Main2186 {

    static int N;
    static int M;
    static int K;
    static char[][] arr;
    static String target;   // 완성할 문자열
    static int[] di = {-1, 0, 0, 1};
    static int[] dj = {0, 1, -1, 0};
    static int[][][] d; // dp 배열

    // 범위 체크
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < M) return true;
        return false;
    }

    // 글자 만들기
    static int solve(int i, int j, int depth) {
        // 이미 해당 지점에서 depth번째 글자를 만들 수 있는 경우
        if (d[i][j][depth] != -1) return d[i][j][depth];

        // 문자열 다 만든 경우
        if (depth == target.length()) {
            return d[i][j][depth] = 1;
        }

        int k = 1;
        int sum = 0;
        char c = target.charAt(depth);
        // 최대 K번 이동
        while (k <= K) {
            for (int dir = 0; dir < 4; dir++) {
                int newI = i + (di[dir] * k);
                int newJ = j + (dj[dir] * k);

                // 범위 벗어나면 패쓰
                if (!isIn(newI, newJ)) continue;
                // 원하는 문자 아니면 패쓰
                if (arr[newI][newJ] != c) continue;

                // 모든 경우의 수 더함
                sum += solve(newI, newJ, depth + 1);
            }
            k++;
        }
        return d[i][j][depth] = sum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2186.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N][M];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = s.charAt(j);
                arr[i][j] = c;
            }
        }

        target = br.readLine();
        d = new int[N][M][target.length() + 1];
        // dp 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int h = 0; h < target.length() + 1; h++) {
                    d[i][j][h] = -1;
                }
            }
        }

        // target의 첫번째 글자로부터 시작
        int answer = 0;
        char c = target.charAt(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] != c) continue;

                answer += solve(i, j, 1);
            }
        }

        System.out.println(answer);
    }
}
