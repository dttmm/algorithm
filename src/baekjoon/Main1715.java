package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 처음에는 dp인줄 알았음
 i~j구간까지의 최소값을 dp로 쪼개가며 구하려고 했는데
 예시처럼
 10 20 40인 경우
 10과 40을 합치는 경우를 고려해줄 수가 없었음

 최소값을 구하기 위해서는
 두 개를 선택했을 때
 두 개의 합이 최소가 되야함 -> 그리디
 모든 숫자를 pq에 넣고
 가장 작은 두 개의 숫자를 뽑아서
 더해준 뒤에 더한 값을 다시 pq에 넣어주고
 pq에 숫자가 2개 이상들어있으면 반복해줌
 */
public class Main1715 {

    static int N;
    static PriorityQueue<Integer> pq;

    static int solve() {
        int total = 0;

        // 카드 묶음이 2개 이상 남아있을 경우
        while (pq.size() >= 2) {
            // 2개 의 카드 묶음을 뽑음
            int n1 = pq.poll();
            int n2 = pq.poll();

            // 더해줌
            int sum = n1 + n2;
            total += sum;
            pq.add(sum);
        }

        return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1715.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            pq.add(n);
        }

        int result = solve();
        System.out.println(result);
    }
}
