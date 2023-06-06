package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 11분 구현 14분
 행렬로 풀었는데 메모리 초과남
 행렬을 long[][]타입으로 사용해서 그런가하고
 int[][]타입으로 바꿨는데도 메모리 초과남

 고민 11분
 흠..
 메모리를 어떻게 더 최적화할 수 있을까 찾아봐도
 답이 안보임
 질문 게시판 슬쩍 봤는데
 피사노 주기를 알고 있어야 풀 수 있는 문제였네
 피보나치 어렵다 어려워

 피사노주기 + 행렬로 풀었는데 메모리 초과 또 남
 그래서 재귀로 풀었음
 행렬이 문제였구먼
 */
public class Main2749 {

    static final int R = 1000000;
    static long N;
    static int[] d;


    static int solve(int n) {
        for (int i = 2; i <= n; i++) {
            d[i] = (d[i - 1] + d[i - 2]) % R;
        }
        return d[n];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2749.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        d = new int[15000000];
        d[1] = 1;

        int p = 1500000;    // 피사노 주기

        int result = solve((int) (N % p));
        System.out.println(result);
    }
}