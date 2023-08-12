package baekjoon.대회2023.제1회_유틸컵_Chapter_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 3분 구현 10분
 구현
 1번 쿼리는 한 행을 돌려주는데
 주의할 점은 행의 끝에서 부터 값을 갱신해야함
 맨 앞에서 값을 갱신하면 0열에 있는 값이
 N-1열까지 모두 동일하게 복사됨

 2번 쿼리는 그냥 배열 돌리기임
 문제에서는 0번 인덱스를 1번으로 사용해서
 배열 돌리기 식이 N - i + 1로 약간 헷갈리게 나옴
 그냥 0번 인덱스를 사용하는 기준을 적용해서 N - i - 1로 배열돌리기 함
 */
public class MainD {

    static int N;
    static int Q;
    static int[][] arr;

    // 1번 쿼리
    static void rotate1(int i) {
        int temp = arr[i][N - 1];

        // 행 돌리기
        for (int j = N - 1; j > 0; j--) {
            arr[i][j] = arr[i][j - 1];
        }

        arr[i][0] = temp;
    }

    // 2번 쿼리
    static void rotate2() {
        int[][] brr = new int[N][N];

        // 배열 돌리기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                brr[j][N - i - 1] = arr[i][j];
            }
        }

        arr = brr;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_유틸컵_Chapter_1/D.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        // 쿼리 받기
        Q = Integer.parseInt(br.readLine());
        for (int k = 0; k < Q; k++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 1번 쿼리인 경우
            if (n == 1) {
                int i = Integer.parseInt(st.nextToken());
                rotate1(i - 1);
            }
            // 2번 쿼리인 경우
            else {
                rotate2();
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
