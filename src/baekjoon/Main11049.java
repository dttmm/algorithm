package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 최적의 값을 구하기 위해서는 모든 경우의 수를 알아야 하니까
 처음에는 조합으로 접근해봄
 근데 조합으로 할 경우 경우의 수가 500!으로 말이 안되는 수가 나옴
 결국 dp라는 건데..
 앞에서 부터 차례로 최적의 값을 구하는 것이 아니라
 중간 어딘가에서 먼저 계산을 해버리면
 순차적인 dp를 구할수가 없는데..
 어케 dp 접근해야될지 몰라서
 헬프!!

 와우
 새로운 dp 접근방식을 배웠당
 넘모 어렵당
 최적의 순서를 고르는 데 있어서
 문제를 이렇게 쪼갤 수도 있구낭
 */
public class Main11049 {

    static int N;
    static Node[] arr;
    static int[][] d;   // i~j 구간 중에서 최소값

    // 행렬 정보
    private static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    static int solve(int i, int j) {
        // 행렬이 하나인 경우
        if (i == j) return 0;

        // 이미 계산한 경우
        if (d[i][j] != 0) return d[i][j];

        // 행렬 2개를 곱하는 경우
        if (j - i == 1)
            return d[i][j] = arr[i].left * arr[i].right * arr[j].right;

        // 구간 중에서 최소값 선택
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {

            // i~k, k+1~j 두 구간으로 쪼개고
            // 각각의 구간의 최소값과
            // 두 구간을 합쳤을 때의 비용의 최소값 구하기
            min = Math.min(min, solve(i, k) + solve(k + 1, j) + arr[i].left * arr[k].right * arr[j].right);
        }

        return d[i][j] = min;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11049.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        d = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            arr[i] = new Node(n1, n2);
        }

        solve(0, N - 1);
        System.out.println(d[0][N - 1]);
    }
}
