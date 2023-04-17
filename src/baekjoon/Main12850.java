package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 처음에는 dp인줄 알고 규칙을 찾으려고 노력했음
 하지만 실패
 알고있는 지식을 초동원 해봤지만
 도저히 아이디어가 안떠오름
 헬프!

 와
 그래프에서 꼭지점에서 꼭지점으로 가는 경우의 수를 구하는 방법이 따로 있었구나
 그래프를 인접 행렬로 만들고
 행렬을 곱하면 곱한 수 만큼 이동 횟수가 되는 군하
 수학의 세계 신비롭다
 */
public class Main12850 {

    static int N;
    final static int mod = 1000000007;
    final static int limit = 8;
    final static int[][] arr = {
            {0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    // 행렬의 곱
    static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[limit][limit];
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                int sum = 0;
                for (int k = 0; k < limit; k++) {
                    sum = (sum + (int) (((long) a[i][k] * b[k][j]) % mod)) % mod;
                }
                c[i][j] = sum;
            }
        }
        return c;
    }

    // 분할 정복
    static int[][] solve(int n) {
        if (n == 1) return arr;

        int[][] a = solve(n / 2);
        if (n % 2 == 1) return multiply(arr, multiply(a, a));
        else return multiply(a, a);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12850.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] result = solve(N);
        System.out.println(result[0][0]);
    }
}
