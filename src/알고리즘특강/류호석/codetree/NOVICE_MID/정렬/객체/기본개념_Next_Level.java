package 알고리즘특강.류호석.codetree.NOVICE_MID.정렬.객체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기본개념_Next_Level {

    private static class Node {
        String id = "codetree";
        int level = 10;

        @Override
        public String toString() {
            return "user " + id + " lv " + level;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Node node1 = new Node();
        Node node2 = new Node();

        node2.id = st.nextToken();
        node2.level = Integer.parseInt(st.nextToken());

        System.out.println(node1);
        System.out.println(node2);
    }
}
