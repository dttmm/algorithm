package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 2분 구현 6분 디버깅 1분
 우선순위 큐
 각 포지션 별로 pq를 만들어서 선수들을 관리함
 각 포지션 별로 가치가 가장 높은 선수 하나 뽑아서
 가치를 -1시킨 후, 다시 pq에 넣음
 이 과정을 K번 반복하고
 마지막에
 각 포지션 별로 가치가 가장 높은 선수 하나를 peek해서
 총 가치를 더해줌

 NPE
 pq를 사용할 때
 empty체크 안해서 틀림
 */
public class Main29160 {

    static int N;
    static int K;
    static PriorityQueue<Integer>[] pqs;    // 포지션 별 pq

    // 우선순위 큐
    static int solve() {
        int total = 0;
        // K번 반복
        for (int k = 0; k < K; k++) {
            // 각 포지션별 가치 높은 선수 뽑기
            for (int i = 1; i <= 11; i++) {
                if (pqs[i].isEmpty()) continue;
                int n = pqs[i].poll() * -1;
                n = n > 0 ? n - 1 : 0;
                pqs[i].add(-1 * n);
            }

            // 마지막 일때만
            if (k != K - 1) continue;
            // 각 포지션별 가치 높은 선수 합 구하기
            for (int i = 1; i <= 11; i++) {
                if (pqs[i].isEmpty()) continue;
                int n = pqs[i].peek() * -1;
                total += n;
            }
        }

        return total;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29160.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pqs = new PriorityQueue[12];

        // 초기화
        for (int i = 1; i <= 11; i++) {
            pqs[i] = new PriorityQueue();
        }

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pqs[p].add(-1 * w);
        }

        // 결과
        int ret = solve();

        System.out.println(ret);
    }
}
