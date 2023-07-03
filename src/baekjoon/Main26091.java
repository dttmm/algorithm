package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 2분 구현 4분
 투포인터 이용하여
 양쪽(l, r)에서 조건 만족하는지 판별해 가며
 범위 좁혀감
 */
public class Main26091 {

    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/26091.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        Arrays.sort(arr);

        int l = 0;  // 왼쪽 포인터
        int r = N - 1;  // 오른쪽 포인터
        int total = 0;
        while (l < r) {
            // 팀을 이룰 수 있는 경우
            if (arr[l] + arr[r] >= M) {
                total++;
                r--;
            }
            l++;
        }

        System.out.println(total);
    }
}
