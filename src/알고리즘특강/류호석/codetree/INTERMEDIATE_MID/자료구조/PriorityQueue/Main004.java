package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main004 {

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int dis;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.dis = x + y;
        }

        void add() {
            this.x += 2;
            this.y += 2;
            this.dis += 4;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dis != o.dis) return this.dis - o.dis;
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Node> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Node newNode = new Node(x, y);
            pq.add(newNode);
        }

        for (int i = 0; i < M; i++) {
            Node node = pq.poll();
            node.add();

            pq.add(node);
        }

        System.out.println(pq.poll());
    }
}

