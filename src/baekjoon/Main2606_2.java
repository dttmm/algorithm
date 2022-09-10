package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2606_2 {

    static Node[] heads;
    static Node[] tails;
    static int N;

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void insert(int n1, int n2) {
        if (heads[n1] == null) {
            Node newNode = new Node(n1);
            heads[n1] = newNode;
            tails[n1] = newNode;
        }

        Node newNode = new Node(n2);

        tails[n1].next = newNode;
        tails[n1] = newNode;
    }

    public static int solve(int start) {
        int result = 0;
        boolean[] visited = new boolean[N + 1];
        int[] queue = new int[N * N];
        int rear = 0;
        int front = 0;

        queue[rear++] = start;
        visited[start] = true;

        while (front != rear) {
            int index = queue[front++];
            Node node = heads[index];
            while (node.next != null) {
                if (!visited[node.next.data]) {
                    visited[node.next.data] = true;
                    result++;
                    queue[rear++] = node.next.data;
                }
                node = node.next;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2606.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        heads = new Node[N + 1];
        tails = new Node[N + 1];

        for (int i = 1; i < M + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            insert(n1, n2);
            insert(n2, n1);
        }
        System.out.println(solve(1));
    }
}
