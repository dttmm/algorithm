package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 6분 구현 7분 디버깅 21분
 그리디
 특정 날짜 T가 되었을때
 퀄리티의 합이 Q를 넘지 않는다면
 운영진들 중에서 가장 역량이 좋은 녀석 하나를 뽑아서
 퀄리티 합에 더해줌

 모든 날짜에 대해 검사가 끝났다면
 남은 운영진들의 역량에 준하는 문제를 만들어서
 총 퀄리티 합에 더해줌

 함정이 있는듯
 운영진의 역량에 준하는 문제를 만들거나
 기존 문제 하나의 퀄리티를 높일 수 있는데
 사실상 기존 문제 하나의 퀄리티를 높이는 경우는
 운영진의 역량에 준하는 문제를 만드는 경우보다 무조건 손해이기 때문에
 무조건 새로운 문제를 만드는 것이 더 이득임

 틀림
 퀄리티 합이 Q를 넘지 않는다면
 한번만 문제를 만드는 것이 아니라
 Q를 넘을 때까지
 남은 운영진들로 문제를 계속 만들어야 한다는 것을 간과
 그래서
 퀄리티 합이 Q를 넘을 때까지 계속 문제를 만들어줌
 */
public class Main29336 {

    static int N;
    static int M;
    static PriorityQueue<Integer> pq;   // 운영진들의 역량 (큰 숫자 우선)

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29336.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue();

        // 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(n * -1); // 큰 숫자 우선
        }

        long total = 0;     // 퀄리티 총합
        int lastDay = 0;    // 마지막 날
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            lastDay = day;

            // 퀄리티 합이 Q가 넘는지 검사
            while (total < target) {
                if (!pq.isEmpty()) {
                    int n = pq.poll() * -1;
                    total += n + day;
                }
                // 더이상 만들 문제가 없는 경우
                else {
                    total = -1;
                    break;
                }
            }
        }

        // 남은 운영진들로 문제 만들기
        if (total != -1) {
            while (!pq.isEmpty()) {
                int n = pq.poll() * -1;
                total += n + lastDay;
            }
        }

        System.out.println(total);
    }
}
