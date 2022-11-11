package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main11651 {

    static int N;
    static Node[] nodes;

    private static class Node implements Comparable<Node> {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (this.y != o.y) return this.y - o.y;
            return this.x - o.x;
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11651.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Node newNode = new Node(x, y);
            nodes[i] = newNode;
        }

        Arrays.sort(nodes);

        for (int i = 0; i < N; i++) {
            Node node = nodes[i];

            bw.write(node + "\n");
        }

        bw.flush();
        bw.close();
    }
}
