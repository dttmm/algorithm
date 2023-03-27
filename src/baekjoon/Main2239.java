package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 백트래킹 문제
 처음에는 그냥 간단하게 구현해봤음
 차례대로 숫자 넣어보면서 맞는 숫자 찾으면 바로 넣었는데
 계속 진행할수록 어떠한 숫자도 넣을 수 없는 칸이 생김
 그래서 어떠한 숫자도 넣을 수 없는 칸이 생긴 경우
 백트래킹으로 이전 작업들을 취소해줘야 됨

 12095번 문제의 소스로 풀 수 있는 문제만 주어진다길래
 12095번 문제 봤는데
 그냥 백트래킹 아이디어가 떡하니 있네;;
 아 우연찮게 아이디어 봐버림;;
 */
public class Main2239 {

    static int[][] arr;
    static boolean[][] row; // 각 열에 어떤 숫자 사용했는지 정보
    static boolean[][] col; // 각 행에 어떤 숫자 사용했는지 정보
    static boolean[][] box; // 각 박스에 어떤 숫자 사용했는지 정보

    static boolean solve(int index) {
        if (index == 81) return true;

        int i = index / 9;
        int j = index % 9;
        int boxIndex = (i / 3) * 3 + j / 3;

        // 이미 숫자 채워진 경우 다음 칸 검사
        if (arr[i][j] != 0) {
            return solve(index + 1);
        }

        for (int k = 1; k <= 9; k++) {
            // 행에 이미 해당 숫자 있으면 패쓰
            if (row[i][k]) continue;
            // 열에 이미 해당 숫자 있으면 패쓰
            if (col[j][k]) continue;
            // 박스에 이미 해당 숫자 있으면 패쓰
            if (box[boxIndex][k]) continue;

            // 해당 칸에 해당 숫자 넣어줌
            arr[i][j] = k;
            row[i][k] = true;
            col[j][k] = true;
            box[boxIndex][k] = true;

            boolean result = solve(index + 1);
            if (result) return true;

            // 다음 칸들 중에서 불가능한 경우 나온 경우 해당 숫자 취소
            arr[i][j] = 0;
            row[i][k] = false;
            col[j][k] = false;
            box[boxIndex][k] = false;
        }

        // 해당 칸에 숫자를 넣을 수 없는 경우
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2239.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[9][9];
        row = new boolean[9][10];
        col = new boolean[9][10];
        box = new boolean[9][10];

        // 입력 받기
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                char c = s.charAt(j);
                int n = c - '0';
                arr[i][j] = n;

                if (n == 0) continue;
                row[i][n] = true;
                col[j][n] = true;

                int boxIndex = (i / 3) * 3 + j / 3;
                box[boxIndex][n] = true;
            }
        }

        solve(0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
