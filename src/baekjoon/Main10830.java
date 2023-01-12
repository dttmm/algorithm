package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 B의 값이 1000억으로 값이 어마무시하다
 이분탐색으로 풀수 있는지 따져봄
 10^11 -> 2^11 * 5^11
 5는 대충 2^2와 2^3사이니까 5 ~= 2^3이라하면
 10^11 ~= 2^11 * 2^33 ~= 2^44
 1000억번 계산을 이분탐색으로 풀면 40번 정도만 계산하면 됨

 우선, 각 행렬을 2^n번 곱했을 때의 값을 d[n]배열에 저장해줌
 그리고 B보다 작은 2의 제곱수중 최대값을 answer에 곱해주고
 B에서 이미 계산한 나머지 녀석들을 재귀로 돌려줌
 B가 5일 경우, d[2]를 answer에 곱하고 나머지 1을 재귀 되고
 원래 행렬을 한번만 더 곱하면 되므로 d[0]을 answer에 곱하게 됨

 B가 14일 경우, d[3]를 answer에 곱하고 나머지 6을 재귀 돌리게 되고
 B가 6이 되면, d[2]을 answer에 곱하고 나머지 2를 재귀 돌리게 됨
 B가 2가 되면, d[1]을 answer에 곱하게 됨
 */
public class Main10830 {

    static int N;
    static long B;
    static int[][][] d;
    static int[][] answer;

    // n보다 작은 2의 제곱수중 최대
    static int findMax(long n) {
        long x = 1;
        int index = -1;
        while (x <= n) {
            x *= 2;
            index++;
        }
        return index;
    }

    // 행렬 곱하기
    static int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum % 1000;
            }
        }
        return result;
    }

    // 이분 탐색으로 풀기
    static void solve(long n) {
        if (n <= 0) return;

        long x = 1;
        while (x <= n) x *= 2;
        x /= 2;
        int max = findMax(x);

        answer = multiply(answer, d[max]);
        solve(n - x);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10830.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        int max = findMax(B);
        d = new int[max + 1][N][N];
        answer = new int[N][N];

        // 단위 행렬 만들기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) continue;
                answer[i][j] = 1;
            }
        }

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                d[0][i][j] = n;
            }
        }

        // 2의 제곱수 결과 저장
        for (int i = 1; i <= max; i++) {
            d[i] = multiply(d[i - 1], d[i - 1]);
        }

        solve(B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
