package programmers.lv0;

import java.util.*;

public class 등수매기기 {

    private class Node implements Comparable<Node> {
        int score;
        int rank;

        public Node(int score) {
            this.score = score;
        }

        public int compareTo(Node o) {
            return -(this.score - o.score);
        }
    }

    PriorityQueue<Node> pq;

    public int[] solution(int[][] score) {
        int size = score.length;

        int[] answer = new int[size];
        Node[] nodes = new Node[size];
        pq = new PriorityQueue();

        for (int i = 0; i < size; i++) {
            int[] scores = score[i];
            int sum = scores[0] + scores[1];
            Node newNode = new Node(sum);

            pq.add(newNode);
            nodes[i] = newNode;
        }

        int preScore = 0;
        int rank = 0;
        int count = 1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (preScore == node.score) {
                count++;
            } else {
                rank += count;
                count = 1;
            }
            node.rank = rank;

            preScore = node.score;
        }

        for (int i = 0; i < size; i++) {
            answer[i] = nodes[i].rank;
        }

        return answer;
    }
}
