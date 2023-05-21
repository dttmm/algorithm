package baekjoon.대회2023.인하대학교_프로그래밍_경진대회_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 Hard문제 시간초과난 코드로 돌려봄
 백트래킹으로 풀음

 계속 값을 더하다가(sum)
 sum이 M 이상이 되었을 경우
 그 이후 경우의 수는 6^(남은 깊이)로 정답에 더해줌

 그리고 이전에 어떤 j열에서 선택했는지 값을 받아서
 6개중에서 같은 j열을 또 선택하게 되면은
 sum에 절반만 더해줌
 */
public class MainB {

    static int N;
    static int M;
    static int[][] arr;
    static long answer;

    static void solve(int k, int pick_j, int sum) {

        // 진척도가 이미 M을 달성했을 경우
        if (sum >= M) {
            answer = (answer + (long) Math.pow(6, N - k));
            return;
        }

        // N개의 임무를 다 수행했을 경우
        if (k == N) return;

        // 다음 임무 선택
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 2; i++) {

                // 같은 장소를 또 선택했을 경우
                if (j == pick_j) solve(k + 1, j, sum + arr[i][j] / 2);

                else solve(k + 1, j, sum + arr[i][j]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/인하대학교_프로그래밍_경진대회_Open_Contest/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[2][3];
        answer = 0;

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;
            }
        }

        // 처음에 각각의 장소에서 시작
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                solve(1, j, arr[i][j]);
            }
        }

        System.out.println(answer);
    }
}
