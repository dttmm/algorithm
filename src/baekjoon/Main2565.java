package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main2565 {

    static int N;
    static List<Node> nodeList;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int a;
        int b;
        int count = 0;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            return this.a - o.a;
        }
    }

    static boolean check() {
        for (int i = 0; i < nodeList.size() - 1; i++) {
            int b1 = nodeList.get(i).b;

            for (int j = i + 1; j < nodeList.size(); j++) {
                int b2 = nodeList.get(j).b;

                if (b1 > b2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2565.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nodeList = new ArrayList();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            Node newNode = new Node(a, b);
            nodeList.add(newNode);
        }

        Collections.sort(nodeList);

        for (int i = 0; i < N - 1; i++) {
            int b1 = nodeList.get(i).b;

            for (int j = i + 1; j < N; j++) {
                int b2 = nodeList.get(j).b;

                if (b1 > b2) {
                    nodeList.get(i).count = nodeList.get(i).count + 1;
                    nodeList.get(j).count = nodeList.get(j).count + 1;
                }
            }
        }

        pq = new PriorityQueue(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return -(o1.count - o2.count);
            }
        });

        pq.addAll(nodeList);

        int answer = 0;
        while (!pq.isEmpty()) {
            if (!check()) {
                Node node = pq.poll();
                nodeList.remove(node);
                answer++;
            } else break;
        }

        System.out.println(answer);
    }
}
