package 알고리즘특강.류호석.codetree.NOVICE_MID.정렬.객체;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 기본개념007 {

    private static class Node {
        String code;
        String place;
        int time;

        public Node(String code, String place, int time) {
            this.code = code;
            this.place = place;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Node node = new Node(st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));

        System.out.println("secret code : " + node.code);
        System.out.println("meeting point : " + node.place);
        System.out.println("time : " + node.time);
    }
}

