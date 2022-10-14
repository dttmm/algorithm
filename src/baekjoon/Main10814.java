package baekjoon;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main10814 {

    private static class Node implements Comparable<Node> {
        int age;
        String name;
        int index;

        public Node(int age, String name, int index) {
            this.age = age;
            this.name = name;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.age != o.age)
                return this.age - o.age;
            return this.index - o.index;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10814.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            Node newNode = new Node(age, name, i);
            pq.add(newNode);
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            bw.write(node.age + " " + node.name);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
