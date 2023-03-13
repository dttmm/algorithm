package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 함정 문제?
 B는 재배열하면 안된다고 했지만
 사실 재배열해도 상관없음

 A는 작은 수부터 정렬하고
 B는 큰 수부터 정렬하고
 둘의 곱함
 */
public class Main1026 {

    static int N;
    static Integer[] arr;
    static Integer[] brr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1026.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];
        brr = new Integer[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            brr[i] = n;
        }

        Arrays.sort(arr);
        Arrays.sort(brr, Collections.reverseOrder());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += arr[i] * brr[i];
        }

        System.out.println(answer);
    }
}
