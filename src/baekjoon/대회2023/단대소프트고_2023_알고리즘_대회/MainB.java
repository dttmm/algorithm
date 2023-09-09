package baekjoon.대회2023.단대소프트고_2023_알고리즘_대회;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 4분 구현 3분 디버깅 4분
 누적합
 i번째 교실까지 오는데 획득한 점수를 누적합으로 구해줌
 누적합을 정렬하여
 가장 높은 값부터 K번 더하면 끝

 틀림
 누적합 편하게 구하려고 배열의 크기를 N+1로 잡았는데
 정렬하는 과정에서 사용하지 않는 0번 인덱스까지 정렬되는 바람에
 틀림
 결국 배열 크기 N으로 다시 누적합 구함
 */
public class MainB {

    static int N;
    static int K;
    static int[] arr;
    static long[] sum;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/단대소프트고_2023_알고리즘_대회/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        sum = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;

            if (i == 0) sum[i] = n;
            else sum[i] = sum[i - 1] + n;
        }

        Arrays.sort(sum);

        long total = 0;
        for (int i = 0; i < K; i++) {
            total += sum[N - 1 - i];
        }

        System.out.println(total);
    }
}
