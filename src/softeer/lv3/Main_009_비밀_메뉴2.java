package softeer.lv3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 LCS 문제
 */
public class Main_009_비밀_메뉴2 {


    public class Main {
        static int N;
        static int M;
        static int[] arr;   // 첫번째 입력 저장할 배열
        static int[][] d;

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            arr = new int[N + 1];
            d = new int[M + 1][N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;
            }

            int max = 0;
            st = new StringTokenizer(br.readLine());
            // 두번째 입력 돌면서
            for (int i = 1; i <= M; i++) {
                int n = Integer.parseInt(st.nextToken());
                // 첫번째 입력값과 비교
                for (int j = 1; j <= N; j++) {
                    if (n == arr[j]) {
                        d[i][j] = d[i - 1][j - 1] + 1;
                        max = Math.max(max, d[i][j]);
                    }
                }
            }

            System.out.println(max);
        }
    }
}
