package baekjoon.대회2023.제1회_유틸컵_Chapter_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 13분 구현 19분
 구현
 그냥 문제에서 주어진대로 구현하면 됨
 설계 단계에서 로직 꼼꼼히 설계함
 */
public class MainI {

    static int I;
    static int N;
    static int K;
    static char[][] arr;
    static String COLOR;    // 잉크 문자열
    static int colorIndex;  // 현재 칠할 색
    static String COMMAND;  // 커맨드
    static int cursor_i;    // 사각형의 위치
    static int cursor_j;    // 사각형의 위치
    static int inkAmount;   // 현재 잉크양
    static int[] di = {-1, 1, 0, 0};    // 상하좌우 방향
    static int[] dj = {0, 0, -1, 1};
    static Map<Character, Integer> dirMap;  // U,D,L,R 커맨드의 방향 정보 담음

    // 범위 검사
    static boolean isIn(int i, int j) {
        if (i >= 0 && i < N && j >= 0 && j < N) return true;
        return false;
    }

    // 점프
    static void jump() {
        // 사각형이 이동할 수 있는 범위 구함
        int start_i = cursor_i - inkAmount;
        int start_j = cursor_j - inkAmount;
        int end_i = cursor_i + inkAmount;
        int end_j = cursor_j + inkAmount;

        // i행 부터 검사
        for (int i = start_i; i <= end_i; i++) {
            // 범위 체크
            if (i < 0 || i >= N) continue;
            // j열 검사
            for (int j = start_j; j <= end_j; j++) {
                // 범위 체크
                if (j < 0 || j >= N) continue;
                // 잉크가 도달할 수 있는 거리인지 체크
                if (Math.abs(cursor_i - i) + Math.abs(cursor_j - j) > inkAmount) continue;
                // 장애물이 아닌 경우 패쓰
                if (arr[i][j] == '.') continue;

                // 현재 색 고르고
                int color = colorIndex % I;
                // 색 칠함
                arr[i][j] = COLOR.charAt(color);
            }
        }
        // 색 인덱스 증가
        colorIndex++;
        // 잉크양 초기화
        inkAmount = 0;
    }

    // 이동
    static void move(char c) {
        // 방향 정보 가져옴
        int dir = dirMap.get(c);
        int newI = cursor_i + di[dir];
        int newJ = cursor_j + dj[dir];

        // 범위 벗어나면 패쓰
        if (!isIn(newI, newJ)) return;
        // 이동할 수 없으면 패쓰
        if (arr[newI][newJ] != '.') return;

        // 좌표값 갱신
        cursor_i = newI;
        cursor_j = newJ;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_유틸컵_Chapter_2/I.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        I = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        COLOR = br.readLine();
        arr = new char[N][N];
        colorIndex = 0;
        inkAmount = 0;
        dirMap = new HashMap();
        dirMap.put('U', 0);
        dirMap.put('D', 1);
        dirMap.put('L', 2);
        dirMap.put('R', 3);

        // 입력 받기
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);

                // 장애물
                if (c == '#') {
                    arr[i][j] = '#';
                    continue;
                }
                //빈칸
                else arr[i][j] = '.';

                // 사각형
                if (c == '@') {
                    cursor_i = i;
                    cursor_j = j;
                }
            }
        }

        COMMAND = br.readLine();

        // 커맨드대로 명령 수행
        for (int i = 0; i < COMMAND.length(); i++) {
            char c = COMMAND.charAt(i);

            // 잉크 충전
            if (c == 'j') inkAmount++;
            // 점프
            else if (c == 'J') jump();
            // 이동
            else move(c);
        }

        // 사각형 위치 표시
        arr[cursor_i][cursor_j] = '@';
        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
