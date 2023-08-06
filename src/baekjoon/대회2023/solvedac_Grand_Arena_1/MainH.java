package baekjoon.대회2023.solvedac_Grand_Arena_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 4분 구현 5분
 구현
 입력을 받을 때마다 일일히 값을 업데이트 시켜주면 너무 오래 걸릴 것으로 보임
 어느 행, 열을 얼마나 업데이트 시켜야되는지 정보를 저장하고 있다가
 마지막에 한꺼번에 업데이트 시켜줌
 row[]에는 i행을 얼마나 업데이트 해야하는지
 colum[]에는 j열을 얼마나 업데이트 해야하는지 저장함
 */
public class MainH {

    static int N;
    static int M;
    static int Q;
    static int[][] arr;
    static int[] row;   // i행을 value만큼 업데이트 시킴
    static int[] colum; // j열을 value만큼 업데이트 시킴

    // 행, 열 값 업데이트
    static void solve() {
        // 행 값 업데이트
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] += row[i];
            }
        }

        // 열 값 업데이트
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                arr[i][j] += colum[j];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_1/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        row = new int[N];
        colum = new int[M];

        // 입력 받기
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int index = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // 열 업데이트의 경우
            if (q == 1) row[index - 1] += v;
            // 행 업데이트의 경우
            else colum[index - 1] += v;
        }

        // 행, 열 값 업데이트
        solve();

        // 업데이트된 값 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int n = arr[i][j];
                sb.append(n + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
