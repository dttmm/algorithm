package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 1트에서는 방법 고민하다 실패하고
 2트에서는 우선순위를 이용해서 풀었음
 어떤 스티커를 선택하게 될때
 해당 스티커의 점수와 해당 스티커를 선택함으로써 잃을 수 있는
 점수의 차이가 작은 것부터 선택하도록 했음
 근데, 해당 차이가 같은 스티커가 여러개 나올 경우 해결할 방법이 없었음

 도저히 안풀려서 답봤는데 dp넹..
 역쉬 dp는 어려웡
 */
// 1시간 30분 + 46분
public class Main9465 {

    static int N;
    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9465.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            N = Integer.parseInt(br.readLine());
            arr = new int[2][N + 1];
            dp = new int[2][N + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    arr[i][j] = n;
                }
            }

            dp[0][1] = arr[0][1];
            dp[1][1] = arr[1][1];

            for (int j = 2; j <= N; j++) {
                for (int i = 0; i < 2; i++) {
                    dp[i][j] = Math.max(dp[(i + 1) % 2][j - 1], dp[(i + 1) % 2][j - 2]) + arr[i][j];
                }
            }

            int answer = Math.max(dp[0][N], dp[1][N]);
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}
