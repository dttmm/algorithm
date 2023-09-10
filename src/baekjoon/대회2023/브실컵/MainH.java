package baekjoon.대회2023.브실컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 7분 구현 9분
 누적합
 */
public class MainH {

    static int N;
    static int M;
    static int A;
    static int[][] arr;
    static int[] sumCol;

    static int solve() {
        int answer = 0;

        int total = 0;

        for (int i = 0; i < A - 1; i++) total += sumCol[i];

        for (int i = A - 1; i < M; i++) {
            total += sumCol[i];
            answer = Math.max(answer, total);
            total -= sumCol[i - A + 1];
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/브실컵/H.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        sumCol = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        for (int j = 0; j < M; j++) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                int n = arr[i][j];
                sum += n;
            }
            sumCol[j] = sum;
        }

        A = Integer.parseInt(br.readLine());

        int result = solve();

        System.out.println(result);
    }
}
