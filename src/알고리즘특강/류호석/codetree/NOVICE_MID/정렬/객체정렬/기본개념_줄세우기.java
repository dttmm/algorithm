package 알고리즘특강.류호석.codetree.NOVICE_MID.정렬.객체정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기본개념_줄세우기 {

    private static class Node implements Comparable<Node> {
        int index;
        int height;
        int weight;

        public Node(int index, int height, int weight) {
            this.index = index;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return height + " " + weight + " " + index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.height != o.height) return o.height - this.height;
            return o.weight - this.weight;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            nodes[i] = new Node(i + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes);

        for (int i = 0; i < N; i++) {
            System.out.println(nodes[i]);
        }
    }
}
