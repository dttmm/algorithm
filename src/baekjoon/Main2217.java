package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 간단한 아이디어 문제
 수를 정렬하고
 작은 것부터 시작해서
 현재 로프가 버틸 수 있는 중량 * 남아있는 로프의 개수를 하면
 현재 로프를 사용하면서 최대로 들어올릴 수 있는 중량 w를 구할 수 있음
 */
public class Main2217 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2217.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int max = 0;
        for (int i = 0; i < N; i++) {
            int n = arr[i];
            max = Math.max(max, n * (N - i));
        }

        System.out.println(max);
    }
}
