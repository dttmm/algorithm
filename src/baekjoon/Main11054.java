package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 너무 복잡하게 생각했었다

 그냥 단순하게 완탐 돌려도
 N이 최대 1000이라서
 1000*1000 = 백만밖에 안되네

 왼쪽에서부터 가장 긴 증가하는 부분수열의 개수 구하고
 오른쪽에서도 한번 더 구하고
 둘의 합 - 1을 하면 끄읕
 */
public class Main11054 {

    static int N;
    static int[] arr;
    static int[] d1;    // 내 기준 왼쪽에서 가장 긴 증가하는 부분수열의 개수
    static int[] d2;    // 내 기준 오른쪽에서 가장 긴 증가하는 부분수열의 개수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11054.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        d1 = new int[N];
        d2 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 내 기준 왼쪽에서 가장 긴 증가하는 부분수열의 개수 구하기
        d1[0] = 1;
        for (int i = 0; i < N; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                // 나보다 작은 숫자들만 통과
                if (arr[j] >= arr[i]) continue;

                // 나보다 작은 숫자들 중 가장 긴 증가하는 부분수열 찾기
                max = Math.max(max, d1[j]);
            }
            d1[i] = max + 1;
        }

        // 내 기준 오른쪽에서 가장 긴 증가하는 부분수열의 개수 구하기
        d2[N - 1] = 1;
        for (int i = N - 1; i >= 0; i--) {
            int max = 0;

            for (int j = N - 1; j > i; j--) {
                if (arr[j] >= arr[i]) continue;

                max = Math.max(max, d2[j]);
            }
            d2[i] = max + 1;
        }

        // 내 기준 왼쪽에서 가장 긴 증가하는 부분수열과
        // 오른쪽에서 가장 긴 증가하는 부분수열 개수 더하기
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, d1[i] + d2[i]);
        }

        // 내가 두번 증복되있으므로 마지막에 하나 뺌
        System.out.println(max - 1);
    }
}
