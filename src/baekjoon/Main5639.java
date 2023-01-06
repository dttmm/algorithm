package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 입력으로 받은 전위 순회와
 출력해야할 후위 순회 사이에서의 규칙을 찾아보려 했으나 찾지 못함

 그래서 입력으로 받은 전위 순회를 이용하여
 직접 이진트리를 만들고
 만든 이진트리를 후위순회 돌림

 입력을 받을 때마다
 root에서 부터 크기 비교를 하여 이진트리 만들었음
 N이 100000이어도
 nlogn -> 100000*17 = 1,700,000정도 밖에 걸리지 않음
 */
public class Main5639 {

    static Node root;
    static StringBuilder sb;

    private static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 이진 트리 생성
    static void addNode(Node node, int n) {
        if (n < node.value) {
            if (node.left == null) {
                node.left = new Node(n);
            } else {
                addNode(node.left, n);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(n);
            } else {
                addNode(node.right, n);
            }
        }
    }

    // 후위 순회
    static void postOrder(Node node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value + "\n");
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5639.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String s = br.readLine();
        root = new Node(Integer.parseInt(s));

        // 입력 받아서 이진 트리 생성
        while (true) {
            s = br.readLine();
            if (s == null) break;

            int n = Integer.parseInt(s);
            addNode(root, n);
        }

        // 생성된 이진 트리 후위 순회
        postOrder(root);

        System.out.println(sb);
    }
}
