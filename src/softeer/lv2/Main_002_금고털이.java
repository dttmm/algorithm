package softeer.lv2;

import java.util.*;
import java.io.*;

/**
 처음에 문제보고 dp여서 깜짝 놀람
 lv2인데 벌써?
 근데 다행히 zero-one dp문제가 아니라
 나눌 수 있는 dp 문제였음 휴
 간단하게 가치가 높은 보물들을 뽑고
 배낭에 최대로 담을 수 있을 때까지 담았음
 */
public class Main_002_금고털이 {

    static int N;
    static int max;

    private static class Node implements Comparable<Node> {
        int value;
        int weight;

        public Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return -(this.value - o.value);
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        max = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            Node newNode = new Node(value, weight);
            pq.add(newNode);
        }

        while (!pq.isEmpty() || max > 0) {
            Node node = pq.poll();

            // 보물이 배낭 한계를 넘어설 경우
            if (node.weight > max) {
                // 남은 한계 만큼 담음
                answer += max * node.value;
                max = 0;
            } else {
                answer += node.weight * node.value;
                max -= node.weight;
            }
        }

        System.out.println(answer);
    }
}
