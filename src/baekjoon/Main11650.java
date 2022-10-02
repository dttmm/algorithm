package baekjoon;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main11650 {

    private static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11650.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Node(x, y));
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            bw.write(node.x + " " + node.y);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
