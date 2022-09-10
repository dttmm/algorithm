package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main1260_2 {

    static int N;
    static int M;
    static int V;
    static Node[] nodes;

    private static class Node {
        int index;
        PriorityQueue<Integer> w1;
        PriorityQueue<Integer> w2;

        public Node(int index) {
            this.index = index;
            w1 = new PriorityQueue(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            w2 = new PriorityQueue(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
        }
    }

    public static String DFS(int start) {
        String result = "";
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[N + 1];
        stack.push(start);
        while (!stack.isEmpty()) {
            int num = stack.pop();
            if (!visited[num]) {
                result += num + " ";
                visited[num] = true;
                Node node = nodes[num];

                while (!node.w1.isEmpty()){
                    int u = node.w1.poll();
                    if (!visited[u]) {
                        stack.push(u);
                    }
                }
            }
        }
        return result;
    }

    public static String BFS(int start) {
        String result = "" + start;
        Queue<Integer> queue = new LinkedList();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int num = queue.poll();
            Node node = nodes[num];

            while (!node.w2.isEmpty()){
                int u = node.w2.poll();
                if (!visited[u]) {
                    queue.add(u);
                    result += " " + u;
                    visited[u] = true;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1260.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        nodes = new Node[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            nodes[n1].w1.add(n2);
            nodes[n2].w1.add(n1);
            nodes[n1].w2.add(n2);
            nodes[n2].w2.add(n1);
        }

        System.out.println(DFS(V));
        System.out.println(BFS(V));
    }
}
