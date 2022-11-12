package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1074 {

    static int answer;

    // n: 전체 범위
    // i, j : 목표 인덱스
    public static void solve(int n, int i, int j, int sum) {

        // 마지막 1칸에 도착한 경우
        if (n == 1) {
            answer = sum;
        } else {

            // 4등분 해서 재귀
            // 어느 등분에 있는지 알아야함
            int mid = n / 2;
            int nn = n * n;

            if (i < mid) {
                // 왼쪽 위
                if (j < mid) {
                    solve(mid, i, j, sum);
                }
                // 오른쪽 위
                else {
                    solve(mid, i, j - mid, sum + nn / 4);
                }
            } else {
                // 왼쪽 아래
                if (j < mid) {
                    solve(mid, i - mid, j, sum + (nn / 4) * 2);

                }
                // 오른쪽 아래
                else {
                    solve(mid, i - mid, j - mid, sum + (nn / 4) * 3);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1074.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        answer = 0;

        int size = (int) Math.pow(2, N);
        solve(size, R, C, 0);

        System.out.println(answer);
    }
}
