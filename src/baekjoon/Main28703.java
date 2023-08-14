package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 1시간+
 문제만 딱 봤을 때는 이분탐색이 떠올랐음
 어떤 기준점을 정하고
 해당 기준점보다 높은 또는 낮은 숫자가 될 때까지 2를 계속 곱해서
 최적의 해를 찾으려고 했는데
 기준점을 바꾼다고 해서 항상 최적의 해로 업데이트 되지 않는 것을 발견

 최소값을 찾아서 최소값을 2배로 만들고
 최적의 해가 업데이트되는 경우 최소값을 2배 해주는 과정을 반복함
 근데 최소값을 2배 해주었는데 최적의 해가 안나오면 반복을 그만 뒀는데
 계속 과정을 반복해주다보면
 향후 어느 시점에서는 최적의 해가 업데이트 되는 경우가 생기네

 최대값을 찾아서
 나머지 원소들에 2배를 계속 곱해주어 최대값보다는 작으면서 가장 큰 수를 만들고
 만든 수와 최대값의 차이의 최소값을 찾으려 했지만
 시간 초과

 결구 도움!
 처음에 최대값 찾는 것은 맞았네
 최대값이 2배로 커진다면
 나머지 원소들도 커질테고
 정답도 2배로 커지기 때문에
 최대값은 고정되하면서
 나머지 원소들을 최대값 전후 크기로 만들어야 됐넹
 */
public class Main28703 {

    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28703.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();

        int limit = 0;  // 최대값
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(n);
            limit = Math.max(limit, n);
        }

        int max = limit;
        int n = pq.peek();
        int answer = max - n;
        // 최대값 보다 작은 수들만
        while (n < limit) {
            n = pq.poll();
            int diff = Math.abs(max - n);   // 최대값과 최소값 차이
            int newN = n * 2;

            answer = Math.min(answer, diff);
            max = Math.max(max, newN);

            pq.add(newN);
        }

        System.out.println(answer);
    }
}
