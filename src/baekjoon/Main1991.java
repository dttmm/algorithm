package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 트리 순회의 기본 문제
 */
public class Main1991 {

    static BufferedWriter bw;
    static int N;
    static Node[] nodes;

    // 전위 순회
    static void preOrder(char nodeChar) throws IOException {
        Node node = nodes[nodeChar - 'A'];

        bw.write(node.data);
        if (node.left != '\0') preOrder(node.left);
        if (node.right != '\0') preOrder(node.right);
    }

    // 중위 순회
    static void inOrder(char nodeChar) throws IOException {
        Node node = nodes[nodeChar - 'A'];

        if (node.left != '\0') inOrder(node.left);
        bw.write(node.data);
        if (node.right != '\0') inOrder(node.right);
    }

    // 후위 순회
    static void postOrder(char nodeChar) throws IOException {
        Node node = nodes[nodeChar - 'A'];

        if (node.left != '\0') postOrder(node.left);
        if (node.right != '\0') postOrder(node.right);
        bw.write(node.data);
    }

    private static class Node {
        char data;
        char left;
        char right;

        public Node(char data, char left, char right) {
            this.data = data;
            this.left = left == '.' ? '\0' : left;
            this.right = right == '.' ? '\0' : right;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1991.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char data = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            Node newNode = new Node(data, left, right);
            nodes[data - 'A'] = newNode;
        }

        preOrder('A');
        bw.newLine();
        inOrder('A');
        bw.newLine();
        postOrder('A');
        bw.newLine();

        bw.flush();
        bw.close();
    }
}
