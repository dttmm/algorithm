package 알고리즘특강.류호석.codetree.NOVICE_MID.정렬.객체정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기본개념_키를기준으로정렬 {

    private static class Node implements Comparable<Node> {
        String name;
        int height;
        int weight;

        public Node(String name, int height, int weight) {
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return name + " " + height + " " + weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.height - o.height;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            nodes[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes);

        for (int i = 0; i < N; i++) {
            System.out.println(nodes[i]);
        }
    }
}
