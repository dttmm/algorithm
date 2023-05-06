package baekjoon.대회2023.부산대학교_CodeRace_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 문제를 잘 읽자
 숫자가 덧칠한 횟수인줄 알았네
 알고보니 서로 다른 숫자였네
 서로 다른 숫자여도 덧칠하는 것을 고려해야됨
 */
public class MainB {

    static int N;
    static int M;
    static int[][] arr;

    // 색칠하기
    static void solve(int i, int j, int n) {
        // 색을 칠할 수 있을 때까지 검사
        // arr[i][j] > 0으로 덧칠하는 것까지 고려함
        while (j < M && arr[i][j] > 0) {
            // 같은 색인 경우 칠함
            if (arr[i][j] == n) arr[i][j] = 0;
            j++;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/부산대학교_CodeRace_Open_Contest/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = arr[i][j];
                if (n == 0) continue;

                solve(i, j, n);
                answer++;
            }
        }


        System.out.println(answer);
    }
}
