package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 특정 좌표 x, y까지의 합을 미리 구해서 sum 배열에 담는다
 sum 배열을 구할 때는 해당 좌표의 위, 왼쪽 값과 arr[x][y]값을 더한 다음
 sum[x-1][y-1]값은 중복으로 더해졌으므로 해당 값을 빼준다

 이제 특정 좌표 범위 값을 구할 때에는
 먼저 sum[x2][y2]값에서 sum[x1-1][y2], sum[x2][y1-1]의 값을 빼주는데
 sum[x1-1][y1-1] 값은 중복으로 빼주게 되므로, 다시 sum[x1-1][y1-1]을 한번 더해주면
 x1,y1 ~ x2,y2 범위의 값이 나온다
 */
public class Main11660 {

    static int N;
    static int M;
    static int[][] arr;
    static int[][] sum;

    // sum 배열 채우기
    static void getSum() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] += arr[i][j];

                // 위에꺼 더하기
                sum[i][j] += sum[i - 1][j];

                // 왼쪽꺼 더하기
                sum[i][j] += sum[i][j - 1];

                // 왼쪽위꺼 빼기
                sum[i][j] -= sum[i - 1][j - 1];

            }
        }
    }

    // 좌표값 범위만큼 더한 결과 반환
    static int solve(int x1, int y1, int x2, int y2) {
        return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11660.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        sum = new int[N + 1][N + 1];

        // 입력 arr에 받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // sum배열 채우기
        getSum();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            bw.write(solve(x1, y1, x2, y2) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
