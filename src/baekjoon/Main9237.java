package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 0분 구현 4분

 묘목들을 순서대로 정렬하고
 오래걸리는 묘목을 먼저 심어가며
 해당 묘목을 심는데까지 걸리는 시간 구함
 구한 시간들 중에서 최대값 찾음

 오래 걸리는 묘목부터 심으면
 제일 오래 걸리는 묘목은 걸리는 시간 + 1일이 걸리고
 제일 빨리 걸리는 묘목은 걸리는 시간 + N일이 걸림

 설계 좀 더 탄탄하게 해야겠다
 구현할 때, 버벅였네
 처음에는 제일 작은 수에다 + N + 1해주면 답일줄 알았는데
 왜 그렇게 생각했지??
 이후에는
 실수로 최대값이 아니라 최소값을 구해버렸음
 */
public class Main9237 {

    static int N;
    static int[] arr;
    static int max;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9237.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            // 묘목 심기
            max = Math.max(max, arr[i] + N - i);
        }

        System.out.println(max + 1);
    }
}
