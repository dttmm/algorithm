package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 방문 처리를 하는 배열을 전역으로 선언하여 관리함
 i행의 경우 for문으로 돌릴 필요 없이
 재귀함수를 호출할 때마다 i행을 하나씩 증가시키고
 j열을 for문으로 돌면서 완탐함

 처음에 i행을 for문으로 돌리는 바람에 시간이 오래 걸렸었음
 */
public class Main9663 {
    static int N;
    static boolean[] visited_j;
    static boolean[] visited_cross1;
    static boolean[] visited_cross2;
    static int answer;

    static void solve(int k, int i) {
        if (k == N) answer++;
        else {
            for (int j = 0; j < N; j++) {

                if (visited_j[j]) continue;
                if (visited_cross1[N - 1 - i + j]) continue;
                if (visited_cross2[i + j]) continue;

                visited_j[j] = true;
                visited_cross1[N - 1 - i + j] = true;
                visited_cross2[i + j] = true;

                solve(k + 1, i + 1);

                visited_j[j] = false;
                visited_cross1[N - 1 - i + j] = false;
                visited_cross2[i + j] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9663.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited_j = new boolean[N];
        visited_cross1 = new boolean[2 * N - 1];
        visited_cross2 = new boolean[2 * N - 1];
        answer = 0;

        solve(0, 0);

        System.out.println(answer);
    }
}
