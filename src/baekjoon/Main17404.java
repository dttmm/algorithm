package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 이차원 dp를 이용하면
 인접한 인덱스에서 색 겹치는 것을 해결할 수는 있는데
 첫번째 집과 마지막 집의 색이 겹치는 상황을 해결할 수가 없음

 그래서 삼차원 dp와 노드를 생각함
 노드에는 비용과 첫번째 집이 무슨 색인지 담고
 dp를 이용해서 첫번째 집의 색과 일치하는 경우를 제외하려고 했는데 실패

 첫번째 집과 마지막 집을 제외하고 나머지 집부터 선택할까 했는데도
 어차피 첫번째 집을 고르는 순간 나머지 집의 선택 결과가 달라짐
 도저히 방법이 생각 안나서 도움!!

 시작점을 고정시키는 방법이 있었군하..
 아이디어 어려벼
 */
public class Main17404 {

    final static int INF = 1000 * 1000;
    static int N;
    static int[][] arr;
    static int[][] d;

    static int solve() {
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (i == k) d[0][i] = arr[0][i];
                else d[0][i] = INF;
            }

            for (int i = 1; i < N; i++) {
                d[i][0] = Math.min(d[i - 1][1], d[i - 1][2]) + arr[i][0];
                d[i][1] = Math.min(d[i - 1][0], d[i - 1][2]) + arr[i][1];
                d[i][2] = Math.min(d[i - 1][1], d[i - 1][0]) + arr[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (i == k) continue;
                min = Math.min(min, d[N - 1][i]);
            }
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17404.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        d = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i][0] = r;
            arr[i][1] = g;
            arr[i][2] = b;
        }

        int answer = solve();
        System.out.println(answer);
    }
}
