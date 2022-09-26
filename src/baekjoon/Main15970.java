package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main15970 {

    private static class Node implements Comparable<Node> {
        int dot;
        int color;
        Node next;
        Node pre;

        public Node(int dot, int color) {
            this.dot = dot;
            this.color = color;
        }

        @Override
        public int compareTo(Node o) {
            return this.dot - o.dot;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15970.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        PriorityQueue<Node> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dot = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            Node node = new Node(dot, color);
            pq.add(node);
        }

        Node[] head = new Node[N + 1];
        Node[] tail = new Node[N + 1];

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            Node tailNode = tail[node.color];
            if (head[node.color] == null) {
                head[node.color] = node;
                tail[node.color] = node;
            } else {
                node.pre = tailNode;
                tailNode.next = node;
                tail[node.color] = node;
            }
        }

        for (int i = 1; i <= N; i++) {
            Node node = head[i];
            int diff = 0;

            while (node != null) {
                int dot = node.dot;
                // head 노드인 경우
                if (node.pre == null) {
                    diff = node.next.dot - dot;
                }
                // tail 노드인 경우
                else if (node.next == null) {
                    diff = dot - node.pre.dot;
                } else {
                    int left = dot - node.pre.dot;
                    int right = node.next.dot - dot;
                    diff = Math.min(left, right);
                }
                sum += diff;
                node = node.next;
            }
        }
        System.out.println(sum);
    }
}
