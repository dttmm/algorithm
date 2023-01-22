package baekjoon.대회2023.제1회_보라매컵_본선_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 그리디로 풀었음
 일단 숫자 정렬하고
 큰 수에서 하나 뽑고, 작은 수에서 하나 뽑아서
 둘의 차이만큼 정답에 더해주고
 마지막 남은 두개를 뽑는 경우
 큰 수를 맨 앞에 두면 최고 실력을 낼 수 있고
 마지막 한개만 남은 경우
 그 마지막 수를 맨 앞에 두었음
 */
public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_보라매컵_본선_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        Arrays.sort(arr);

        int answer = 0;
        int start = 0;
        int end = N - 1;
        while (true) {
            int big = arr[end];
            int small = arr[start];

            // 마지막 2개를 고른 경우
            // 큰 숫자를 맨 앞에 세우면 됨
            if (start + 1 == end) {
                answer += big;
                break;
            }

            // 마지막 숫자 1개가 남는 경우
            // 그 숫자 맨 앞에 세우면 됨
            if (start == end) {
                answer += big;
                break;
            }

            answer += big - small;

            start++;
            end--;
        }

        System.out.println(answer);
    }
}
