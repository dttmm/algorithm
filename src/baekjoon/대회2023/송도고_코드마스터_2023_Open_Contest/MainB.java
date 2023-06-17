package baekjoon.대회2023.송도고_코드마스터_2023_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 4분 구현 4분
 주어진 층 정보를 반대로 생각하여 기존 2차원 배열처럼 생각할 수 있도록 함
 그래서 기존 급식실 위치를 1층 끝이 아니라
 꼭대기 끝(이차원 배열 기준으로 0, M+1)에 두고
 각 반에서 급식실과의 거리를 구하고 반 번호를 이용하여
 pq에 넣어서
 가장 우선순위 높은 것 뽑음
 */
public class MainB {

    static int N;
    static int M;
    static int K;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int diff;
        int num;    // 반 번호

        public Node(int diff, int num) {
            this.diff = diff;
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            if (this.diff != o.diff) return this.diff - o.diff;
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/송도고_코드마스터_2023_Open_Contest/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue();

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int floor = Integer.parseInt(st.nextToken()) - 1;
            int ban = Integer.parseInt(st.nextToken());

            // 급식실 까지의 거리
            int diff = (floor - 0) + (M + 1 - ban);
            pq.add(new Node(diff, i));
        }

        System.out.println(pq.poll().num);
    }
}
