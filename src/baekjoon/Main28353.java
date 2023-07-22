package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 1분 구현 2분
 투포인터 이용하여 두 고양이의 합을 검사함
 고양이의 무게가 K를 넘었을 경우에는
 R포인터를 줄여서 무게를 줄이고
 고양이의 무게가 K이하인 경우
 한 쌍을 찾았으므로
 L포인터와 R포인터 각각 1칸씩 이동하여 다음 고양이 쌍 검사함
 */
public class Main28353 {

    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28353.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 정렬
        Arrays.sort(arr);

        // 투포인터
        int answer = 0;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int sum = arr[L] + arr[R];

            // 고양이 무게 합이 K이하인 경우 <- 쌍을 찾은 경우
            if (sum <= K) {
                L++;
                answer++;
            }
            R--;
        }

        System.out.println(answer);
    }
}
