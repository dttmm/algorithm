package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 아이디어 3분 구현 9분
 가로축과 세로축을 나눠서 검사하기 위해
 배열 2개 만듦

 각각
 범위를 벗어나지않고
 벽을 만나지 않을 때까지
 오른쪽, 아래쪽으로 쭈욱 가면서
 두 칸이상 움직였을 경우
 누울 수 있다고 반환함
 */
public class Main1652 {

    static int N;
    static int[][] arr1;    // 가로 검사할 배열
    static int[][] arr2;    // 세로 검사할 배열

    // 이동
    static boolean solve(int i, int j, int dir, int[][] arr) {
        int count = 0;
        while (i < N && j < N && arr[i][j] == '.') {
            // 세로 검사
            if (dir == 1) {
                arr[i][j] = 'X';
                i++;
            }
            // 가로 검사
            else {
                arr[i][j] = 'X';
                j++;
            }

            count++;
        }
        return count > 1;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1652.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr1 = new int[N][N];
        arr2 = new int[N][N];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                arr1[i][j] = c;
                arr2[i][j] = c;
            }
        }

        int[] answer = new int[2];  // 0: 가로 1: 세로
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 가로 검사
                if (arr1[i][j] != 'X') {
                    boolean result1 = solve(i, j, 0, arr1);
                    if (result1) answer[0]++;
                }

                // 세로 검사
                if (arr2[i][j] != 'X') {
                    boolean result2 = solve(i, j, 1, arr2);
                    if (result2) answer[1]++;
                }
            }
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
}
