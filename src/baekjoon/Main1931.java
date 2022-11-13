package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1931 {

    static int N;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int start;
        int end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o) {
            if (this.end != o.end) return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1931.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Node newNode = new Node(start, end);

            pq.add(newNode);
        }

        int count = 0;
        int limit = -1;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.start >= limit) {
                limit = node.end;
                count++;
            }
        }

        System.out.println(count);
    }
}
