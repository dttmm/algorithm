package 알고리즘특강.류호석.codetree.NOVICE_MID.정렬.객체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기본개념_코드네임 {

    private static class Node {
        String codeName;
        int score;

        public Node(String codeName, int score) {
            this.codeName = codeName;
            this.score = score;
        }

        @Override
        public String toString() {
            return codeName + " " + score;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node[] nodes = new Node[5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        Node minNode = nodes[0];
        for (int i = 1; i < 5; i++) {
            if (nodes[i].score < minNode.score) minNode = nodes[i];
        }
        System.out.println(minNode);
    }
}
