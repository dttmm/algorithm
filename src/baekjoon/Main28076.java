package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 19분 구현 16분
 그리디로 풀었음

 3가지 방향이 반복되는 것을 발견
 반복을 통해 중복을 최대화하면 콘센트 수의 최소값을 구할 수 있음

 처음에는 제일 큰 3개의 멀티탭을 기본 베이스로 해서 3방향으로 각각각 배치한 다음
 작은 놈들을 그 위에 쌓으면 될 줄 알았지만
 10 10 5 5 3 3 같은 경우에는
 10끼리 겹치고 나머지 5 3끼리 겹쳐 쌓으면 최소가 됨

 그래서 첫번째 방향에서 먼저 제일 큰 멀티탭을 쌓을 수 있을 때까지 쌓고
 그다음 두번째 방향에서 다음으로 큰 놈들을 쌓고
 마지막 방향에서 나머지 놈들을 쌓는 방법을 생각

 첫번째, 두번째 방향에서 얼마나 많은 멀티탭을 쌓아야 하는지 bound[]로 관리함
 일단은 각 방향에서 동일하게 멀티탭을 가져간다고 하고
 총 멀티탭의 개수를 3으로 나누어
 나머지 멀티탭이 발생하는 경우
 첫번째, 두번째 방향에서 더 가져가도록 했음

 큰 수가 우선순위가 높도록 정렬하기 위해 -1 곱해줌
 */
public class Main28076 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28076.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = -1 * n;
        }

        // 정렬
        Arrays.sort(arr);

        // 일단 각 방향에서 동일하게 멀티탭을 가져감
        int p = N / 3;
        // 첫번째, 두번째 방향이 가질 수 있는 멀티탭 수
        int[] bound = new int[2];

        for (int i = 0; i < 2; i++) {
            bound[i] = p * (i + 1);
        }

        // 멀티탭 하나가 남는 경우 -> 첫번째 방향이 멀티탭 하나 더 가져감
        if (N % 3 == 1) {
            bound[0]++;
            bound[1]++;
        }
        // 멀티탭 두개가 남는 경우 -> 첫번째, 두번째 방향이 멀티탭 하나씩 더 가져감
        else if (N % 3 == 2) {
            bound[0]++;
            bound[1] += 2;
        }

        if (N == 1) {
            System.out.println(-1 * arr[0]);
        } else if (N == 2) {
            System.out.println(-1 * arr[0] + -1 * arr[1] - 1);
        } else {
            System.out.println(-1 * arr[0] + -1 * arr[bound[0]] + -1 * arr[bound[1]] - 3);
        }
    }
}
