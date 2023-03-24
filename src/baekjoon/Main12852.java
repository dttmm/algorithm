package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 bfs로 풀음
 간단하게 3가지 경우에 대해서 queue를 이용해서 bfs돌림
 visited에 어디서부터 왔는지 경로를 저장해줌
 마지막에 경로 출력할 때, visited에 저장된 경로 반대로 출력하면 끝
 */
public class Main12852 {

    static int N;
    static int[] visited;   // 경로 저장

    private static class Node {
        int n;  // 현재 숫자
        int count;  // 횟수 저장

        public Node(int n, int count) {
            this.n = n;
            this.count = count;
        }
    }

    static Node solve() {
        Queue<Node> queue = new LinkedList();
        queue.add(new Node(N, 0));

        // bfs
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int n = node.n;
            int count = node.count;

            // 1 만들었을 경우
            if (n == 1) return node;

            // 3으로 나눌 수 있을 때
            if (n % 3 == 0 && visited[n / 3] == 0) {
                queue.add(new Node(n / 3, count + 1));
                visited[n / 3] = n;
            }
            // 2로 나눌 수 있을 때
            if (n % 2 == 0 && visited[n / 2] == 0) {
                queue.add(new Node(n / 2, count + 1));
                visited[n / 2] = n;
            }
            // 1 뺄 수 있을 때
            if (n - 1 >= 1 && visited[n - 1] == 0) {
                queue.add(new Node(n - 1, count + 1));
                visited[n - 1] = n;
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12852.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new int[N + 1];

        Node node = solve();

        // 경로 리스트에 담음
        List<Integer> list = new ArrayList();
        int n = node.n;
        while (n != N) {
            list.add(n);
            n = visited[n];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(node.count + "\n");
        sb.append(N + " ");
        // 리스트에 저장된 경로 출력
        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i) + " ");
        }

        System.out.println(sb);
    }
}
