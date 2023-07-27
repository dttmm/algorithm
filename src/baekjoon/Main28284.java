package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 21분 구현 22분
 그리디
 나이브하게 풀면 각 날짜에 총 몇 명이 있는지를 카운트 해주면 됨
 하지만 그러면 시간초과

 그러면 인원수가 변동될 때마다
 인원수 변동되기 전과 인원수 변동된 날 사이의(node.day - prev)의 구간에서
 총 몇 명이 있었는지(total)를 알 수 있다면
 i명이 있는 날은 총 며칠(count[i])이 있었는지 계산 가능

 날짜 정보를 저장할 때
 시작 날짜(start)인지 끝(end) 날짜인지 type 구분해서 넣어주고
 pq에서 꺼낼 때
 날짜가 같은 경우 우선순위는 시작날짜 부터 꺼내야됨
 끝 날짜부터 꺼낸다면 원하는 결과 안나옴
 또한, 꺼낸 날짜가 시작 날짜이면
 현재 날짜의 이전까지의 결과를 갱신하고
 꺼낸 날짜가 끝 날짜이면
 현재 날짜를 포함해서 결과를 갱신해줌

 예제 1번의 경우
 1명이 있는 날은 4일
 2명이 있는 날은 2일 이므로
 가장 요금을 많이 받으려면 4일*100 + 2일*110
 가장 요금을 적게 받으려면 4일*1 + 2일*11을 해야됨
 요금을 많이 받는 경우를 기준으로 살펴보면
 100원을 받는 날은 6일, 10원을 받는 날은 2일임
 사람이 있었던 날은 무조건 100원(가장 비싼 요금)을 받게 됨
 사람이 있었던 모든 날(totalCount) <- (사람이 1명 이상 있었던 날) 을 구한 뒤
 사람이 있었던 모든 날 * 100원을 해주고
 그 다음, totalCount에서 1명이 있는 날(count[1])을 빼주게 되면
 totalCount = 사람이 2명 이상 있었던 날이 되므로
 사람이 2명 이상 있었던 날 * 그 다음 비싼 요금을 곱해줌
 이 과정을 반복

 요금(cost[])을 정렬해주었으므로
 요금을 가장 적게 받으려면
 cost 배열의 인덱스를 반대로 하면 됨

 오잉
 자바로 푼 사람 중에
 내가 최초 정답자
 오예~
 */
public class Main28284 {

    final static int limit = 500000;
    static int N;
    static int M;
    static int[] cost;      // 요금
    static long[] count;    // i명이 있었던 날이 총 며칠인지
    static PriorityQueue<Node> pq;
    static long totalCount; // 1명이라도 카페를 이용한 총 날짜

    // 날짜 정보
    private static class Node implements Comparable<Node> {
        int day;
        int type;   // 0: start, 1: end

        public Node(int day, int type) {
            this.day = day;
            this.type = type;
        }

        @Override
        public int compareTo(Node o) {
            // 날짜가 적은 순
            if (this.day != o.day) return this.day - o.day;
            // 시작 날짜(start) 먼저
            return this.type - o.type;
        }
    }

    // i명이 있었던 날이 총 며칠인지 계산
    static void solve() {
        int prev = 1;   // 이전 날짜
        int total = 0;  // 현재 날짜까지 몇 명이 이용하고 있었는지

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // 시작 날짜인 경우
            if (node.type == 0) {
                count[total] += node.day - prev;
                total++;
                prev = node.day;
            }
            // 끝 날짜인 경우
            else {
                count[total] += node.day - prev + 1;
                total--;
                prev = node.day + 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28284.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new int[N + 1];
        count = new long[limit + 1];
        pq = new PriorityQueue();
        totalCount = 0;

        // 요금 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            cost[i] = n;
        }

        // 이용 날짜 입력 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            pq.add(new Node(s, 0));
            pq.add(new Node(e, 1));
        }

        // i명이 있었던 날이 총 며칠인지 계산
        solve();

        // 요금 정렬
        Arrays.sort(cost);

        // 1명이라도 카페를 이용한 날 계산
        for (int i = 1; i <= N; i++) {
            totalCount += count[i];
        }

        // 최대 수익, 최소 수익 계산
        long high = 0;
        long low = 0;
        for (int i = 1; i <= N; i++) {
            high += totalCount * cost[N - i + 1];
            low += totalCount * cost[i];
            totalCount -= count[i];
        }

        System.out.println(low + " " + high);
    }
}
