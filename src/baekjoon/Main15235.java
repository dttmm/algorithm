package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 큐를 이용하여 간단하게 구현
 피자를 나눠줄 때마다 count를 증가시키며
 큐를 돌면서
 피자를 다 받지 못했으면 게속 큐에 추가해줌
 */
public class Main15235 {

    static int N;
    static Queue<Node> queue;
    static int[] answer;

    private static class Node {
        int index;
        int num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    static void solve() {
        int count = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();

            count++;
            node.num--;

            if (node.num == 0) answer[node.index] = count;
            else queue.add(node);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15235.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        queue = new LinkedList();
        answer = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            queue.add(new Node(i, n));
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i] + " ");
        }
        System.out.println(sb);
    }
}
