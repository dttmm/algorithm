package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 누적합의 느낌이 싸악 왔다
 근데 마이너스가 껴있어서 좀 까다로웠다
 일단 누적합을 싸악 구해준뒤

 전체 탐색을 하면서
 해당 인덱스까지의 누적합의 최소값(min)을 구해주고
 현재 누적합과 최소값(min)의 차이(diff)를 구해줌
 차이(diff)의 최대값(max)이 정답

 단, 주어진 수가 모두 0이하인 경우에는
 max가 0이 되는데
 이 경우에는 그냥 주어진 수에서 최대값을 정답으로 고르면 됨
 */
public class Main1912 {

    static int N;
    static int[] arr;
    static int[] sum;   // 누적합

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1912.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sum = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            sum[i] = n;

            if (i == 0) continue;
            sum[i] += sum[i - 1];
        }

        int min = 0;
        int max = 0;
        // 각 지점에서의 최소값과 누적합의 차이를 계산
        for (int i = 0; i < N; i++) {
            min = Math.min(min, sum[i]);

            int diff = sum[i] - min;
            max = Math.max(max, diff);
        }

        // 숫자들이 모두 0이하인 경우에는 max가 0이므로
        // 가장 큰 수가 답이됨
        if (max == 0) {
            Arrays.sort(arr);
            max = arr[N - 1];
        }

        System.out.println(max);
    }
}
