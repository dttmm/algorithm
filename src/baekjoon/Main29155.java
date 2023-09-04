package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 설계 6분 구현 6분
 그리디
 각 난이도 별로 몇 문제를 풀어야하는지 arr[난이도]에 담고
 각 난이도 별로 걸리는 시간을 lists[난이도]에 담음
 lists를 정렬한다음
 풀어야하는 문제 수 만큼 탐색하면서
 걸리는 시간 t를 더해줌
 같은 난이도인 경우 pre변수를 이용해서 걸리는 시간 차이를 구함
 모든 난이도를 적어도 1문제씩 풀어야 하니
 난이도가 증가하는 횟수는 4번이므로
 마지막에 4*60=240을 더해줌
 */
public class Main29155 {

    static int N;
    static int[] arr;   // 난이도 별로 몇 문제를 풀어야하는지
    static List<Integer>[] lists;   // 난이도 별로 걸리는 시간

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29155.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[6];
        lists = new List[6];
        int answer = 0;

        // 초기화
        for (int i = 1; i <= 5; i++) {
            lists[i] = new ArrayList();
        }

        // 입력 받기 (난이도 별로 몇 문제를 풀어야하는지)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 5; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        // 입력 받기 (난이도별 시간)
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            lists[n].add(t);
        }

        // 정렬
        for (int i = 1; i <= 5; i++) {
            Collections.sort(lists[i]);
        }

        // 각 난이도 별로 짧게 걸리는 문제 풀기
        for (int i = 1; i <= 5; i++) {
            int n = arr[i]; // 풀어야 하는 문제 수
            int pre = lists[i].get(0);  // 이전 문제를 푸는데 걸리는 시간

            // 난이도에 해당하는 문제 수만큼 풀기
            for (int j = 0; j < n; j++) {
                int t = lists[i].get(j);
                answer += t;
                answer += t - pre;
                pre = t;
            }
        }

        // 난이도 증가 시간 추가
        answer += 240;
        System.out.println(answer);
    }
}
