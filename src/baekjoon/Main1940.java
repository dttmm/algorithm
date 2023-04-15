package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 재료의 개수를 세서 count배열에 넣고
 각 재료를 돌면서
 합칠 수 있는 재료가 있는지 확인하여
 합침

 합치고나서 두 재료의 개수를 count배열에서 빼줌
 생각보다 범위 고려해줘야 될 부분이 많넹
 */
public class Main1940 {

    static int N;
    static int M;
    static int[] arr;
    static int[] count; // 각 재료의 개수

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1940.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new int[100001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            count[n]++;
            arr[i] = n;
        }

        int answer = 0;
        // 재료를 돌면서
        for (int i = 0; i < N; i++) {
            int n = arr[i];
            int target = M - n;

            // 범위 벗어나는 경우 고려해줌
            if (target <= 0) continue;
            if (target > 100000) continue;

            // 필요한 재료와 현재 재료가 같을 경우
            if (n == target) {
                // 같은 재료 2개 이상 필요함
                if (count[target] <= 1) continue;
            } else {
                if (count[target] <= 0) continue;
            }

            answer++;
            count[target]--;
            count[n]--;
        }

        System.out.println(answer);
    }
}
