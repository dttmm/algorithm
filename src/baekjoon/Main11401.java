package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 이차원 dp를 이용해서 d[n][k]값을 저장하려고 했지만
 4백만*4백만 배열을 만들기에는 공간이 부족쓰
 그래서 1차원 dp로 어떻게 최적화를 할 수 있지 않을까 생각함
 k를 저장할 크기가 4백만인 배열 만들고
 n만큼 for문을 돌리면서 배열의 k값을 갱신해 나갔는데
 n*k번 연산이 발생해서 시간초과가 나는군하..

 혹시 행렬과 분할 정복으로 풀 수 있지 않을까
 식을 세워봤지만 실패
 결국 시간과 메모리를 둘 다 최적화할 방법을 찾지 못해서
 도움!!

 아..
 페르마의 소정리 공식을 모르면 못푸는 문제였네
 팩토리얼 구하는 것을 재귀로 하니까 스택오버플로우나서
 반복문으로 팩토리얼 구함
 설계 5분 구현 18분
 */
public class Main11401 {

    static final int R = 1000000007;
    static int N;
    static int K;
    static long[] d;

    // 팩토리얼 값 구하기
    static void setFactorial() {
        for (int i = 1; i <= N; i++) {
            d[i] = (d[i - 1] * i) % R;
        }
    }

    // 분모의 역원 구하기
    static long solve(long n, int p) {
        if (p == 1) return n;

        long half = solve(n, p / 2);

        if (p % 2 == 1) return (((half * half) % R) * n) % R;
        else return (half * half) % R;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11401.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        d = new long[N + 1];
        d[0] = 1;

        // 각 숫자들의 팩토리얼 값 구함
        setFactorial();

        // 페르마의 소정리
        long n = (d[N - K] * d[K]) % R;
        long answer = (d[N] * solve(n, R - 2)) % R;
        System.out.println(answer);
    }
}
