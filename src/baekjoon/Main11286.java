package baekjoon;

import java.io.*;
import java.util.PriorityQueue;

public class Main11286 {

    static int N;

    private static class Node implements Comparable<Node> {
        int data;
        int realData;

        public Node(int realData) {
            this.realData = realData;
            this.data = Math.abs(realData);
        }

        @Override
        public int compareTo(Node o) {
            if (this.data != o.data) return this.data - o.data;
            return this.realData - o.realData;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11286.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll().realData + "\n");
            } else {
                pq.add(new Node(x));
            }
        }

        bw.flush();
        bw.close();
    }
}
