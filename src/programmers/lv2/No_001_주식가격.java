package programmers.lv2;
import java.util.*;

public class No_001_주식가격 {

    class Solution {

        class Node implements Comparable<Node> {
            int index;
            int price;

            public Node(int index, int price) {
                this.index = index;
                this.price = price;
            }

            public int compareTo(Node o) {
                return -(this.price - o.price);
            }
        }

        public int[] solution(int[] prices) {
            int[] answer = new int[prices.length];
            PriorityQueue<Node> pq = new PriorityQueue();

            for (int i = 0; i < prices.length; i++) {
                int cur_price = prices[i];

                // 현재 가격보다 높은 가격들 뽑음
                while (!pq.isEmpty() && pq.peek().price > cur_price) {
                    Node node = pq.poll();
                    answer[node.index] = i - node.index;
                }

                Node newNode = new Node(i, cur_price);
                pq.add(newNode);
            }

            // 남아 있는 pq 정리
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                answer[node.index] = prices.length - 1 - node.index;
            }

            return answer;
        }
    }
}
