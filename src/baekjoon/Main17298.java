package baekjoon;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main17298 {
    static int N;
    static Stack<Node> stack;
    static int[] answer;
    static BufferedWriter bw;

    private static class Node {
        int index;
        int num;

        public Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17298.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        answer = new int[N];
        stack = new Stack<>();
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur_num = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.num < cur_num) {
                    answer[node.index] = cur_num;
                } else {
                    stack.push(node);
                    break;
                }
            }
            stack.push(new Node(i, cur_num));
        }

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            answer[node.index] = -1;
        }

        for (int i = 0; i < N; i++) {
            bw.write(answer[i] + " ");
        }
        bw.flush();
        bw.close();
    }
}
