package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 3분 구현 5분
 완탐
 처음에는 부분 수열을 구하는 문제라서 투포인터를 생각했었음
 투포인터 만지작거리다 안돼서
 혹시 완탐을 생각해봤는데
 500*5000 = 2천5백만이라서 충분히 가능하넴
 입력 받은 배열(arr[])을 순회하면서
 자신의 위치에서 가능한 증가하는 부분 수열의 개수(count[])를 구해줌

 자신의 왼쪽에 있는 숫자들 중에서
 자신보다 작은 놈들의 증가하는 부분 수열의 개수의 합을 구해서
 자신 위치에서의 증가하는 부분 수열의 개수를 갱신시킴
 예를 들어
 1 2 3의 경우
 2는 1이 가지고 있는 1과, 자기 자신 1을 더하여 답이 2가되고
 3은 1이 가지고 있는 1과, 2가 가지고 있는 2와, 자기 자신을 더하여 4가 됨
 */
public class Main22971 {

    static int MOD = 998244353;
    static int N;
    static int[] arr;
    static long[] count;    // i인덱스에서의 증가하는 부분 수열의 개수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/22971.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new long[N];

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 완탐
        for (int i = 0; i < N; i++) {
            int n = arr[i];

            long sum = 0;
            // 자신의 왼쪽에서
            for (int j = 0; j < i; j++) {
                if (n <= arr[j]) continue;
                // 자신보다 작은 수들의 위치에서
                // 증가하는 부분 수열의 개수를 더해줌
                sum = (sum + count[j]) % MOD;
            }
            count[i] = sum + 1;
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(count[i] + " ");
        }
        System.out.println(sb);
    }
}
