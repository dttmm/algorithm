package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1시간 36분 + 1시간 15분
public class Main1707 {

    static int N;
    static int M;
    static Node[] heads;
    static Node[] tails;
    static int[] visited;

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

        if (heads[n2] == null) {
            Node newNode = new Node(n2);
            heads[n2] = newNode;
            tails[n2] = newNode;
        }

        Node newNode2 = new Node(n2);
        tails[n1].next = newNode2;
        tails[n1] = newNode2;

        Node newNode1 = new Node(n1);
        tails[n2].next = newNode1;
        tails[n2] = newNode1;
    }

    public static boolean solve(int start) {
        Node startNode = heads[start];
        if (startNode == null) return true;

        Node node = startNode;
        while (node.next != null) {
            Node nextNode = node.next;

            // 방문하지 않은 노드인 경우
            if (visited[nextNode.data] == 0) {
                visited[nextNode.data] = 3 - visited[startNode.data];
                boolean result = solve(nextNode.data);
                if (!result) return false;
            }
            // 방문했는데 인접한 노드끼리 팀이 같은 경우
            else if (visited[nextNode.data] == visited[startNode.data]) {
                return false;
            }

            node = nextNode;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1707.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test_case = Integer.parseInt(br.readLine());

        for (int T = 1; T <= test_case; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            heads = new Node[N + 1];
            tails = new Node[N + 1];
            visited = new int[N + 1];

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());

                insert(n1, n2);
            }

            boolean flag = true;

            for (int i = 1; i < N + 1; i++) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    flag = solve(i);
                }
                if (!flag) break;
            }

            if (flag) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
