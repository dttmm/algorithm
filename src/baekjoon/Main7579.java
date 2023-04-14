package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 미팅 문제처럼 접근하려고 했는데 실패
 모든 경우의 수를 만족 할 수가 없었음

 배낭 문제를 1차원dp로 푸는 것처럼 접근 해봤는데
 최소값을 구할 수가 없었음

 백트래킹은 당연히 시간초과가 날텐데
 음

 결국 헬프

 와
 j열을 메모리로 사용했는데
 비용으로 사용해야 됐었군하
 어쩐지 메모리로 사용할 경우
 최대 범위를 어디까지 해야되나
 M으로 해야되나
 총 메모리 합으로 해야되나
 기준이 안섰었는데

 비용을 열로 잡고
 총 비용을 최대 범위로 잡으니까
 dp 크기도 그렇게 크지 않넹..
 */
public class Main7579 {

    static int N;
    static int M;
    static int[] memories;
    static int[] costs;
    static long[][] d;  // i번째 앱까지 j 비용으로 최대 얻을 수 있는 메모리
    static int total;   // 총 비용의 합

    // 이차원 dp
    static int solve() {
        for (int i = 1; i <= N; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int j = 0; j <= total; j++) {

                if (j - cost < 0)
                    d[i][j] = d[i - 1][j];
                else
                    d[i][j] = Math.max(d[i - 1][j], d[i - 1][j - cost] + memory);

                // 마지막 앱이 아닌 경우 패쓰
                if (i != N) continue;
                // 필요한 메모리 확보를 못 했으면 패쓰
                if (d[i][j] < M) continue;

                // 필요한 메모리 확보했을 때의 비용 리턴
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7579.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memories = new int[N + 1];
        costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            costs[i] = n;
            total += n;
        }
        d = new long[N + 1][total + 1];

        System.out.println(solve());
    }
}
