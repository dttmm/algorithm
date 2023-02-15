package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 어떤 것을 먼저 합치느냐에 따라 뒤에 결과들은 예측할 수 없도록 결과가 바뀐다
 어떤 것을 합칠지 한번에 따악 골라야 될 것 같아서
 우선순위를 이용해서 구함
 어떤 것을 골랐을 때
 고른 것으로 인해 점수가 얼마나 커지는지를 계산해가면
 점수 상승폭이 가장 작은 것을 골라갔는데..

 지금 생각해보니
 우선순위가 동일한 것이 여러개 있을 때
 처리를 할 수가 없네
 어라 이런 문제 풀어 봤는데..

 암튼 못 풀어서 문제 유형봄
 dp길래 dp적으로 사고했지만 실패하여
 풀이봄

 풀이들은 죄다 바텀업 방식으로 푼 복붙코드밖에 없어서
 탑다운 방식으로 구현해봄
 dp 어려웡..
 */
public class Main11066 {

    static int N;
    static int[] arr;
    static long[][] d;
    static long[] sum;

    // start부터 end까지의 총 합
    public static long getSum(int start, int end) {
        if (start == 0) return sum[end];
        return sum[end] - sum[start - 1];
    }

    public static long solve(int start, int end) {
        if (start == end) return arr[start];

        long min = Long.MAX_VALUE;
        for (int i = start; i < end; i++) {
            if (start != i && d[start][i] == 0) d[start][i] = solve(start, i);
            if (i + 1 != end && d[i + 1][end] == 0) d[i + 1][end] = solve(i + 1, end);

            min = Math.min(min, d[start][i] + d[i + 1][end]);
        }
        d[start][end] = min + getSum(start, end);
        return d[start][end];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11066.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new int[N];
            d = new long[N][N];
            sum = new long[N];

            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;

                if (i == 0) {
                    sum[i] = n;
                    continue;
                }
                sum[i] = sum[i - 1] + n;
            }

            long answer = solve(0, N - 1);

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}
