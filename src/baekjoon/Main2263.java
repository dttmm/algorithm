package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 중위 순회 결과와 후위 순회 결과 사이의 관계를 찾으려고 했음
 트리가 다음과 같이 생긴 경우
    1
  2   3
 4 5 6 7
 중위 순회 결과: 4 2 5 1 6 3 7
 후위 순회 결과: 4 5 2 6 7 3 1

 부모 노드인 1,2,3 노드의 관계를 분석해봄
 중위 순회의 경우 부모 노드에서 자식노드가 양쪽으로 찢어져 있는 형태이고
 후위 순회의 경우 오른쪽에서부터 부모노드가 자식 노드를 펼친 형태임

 후윈 순회 결과(brr)에서 맨 뒤(nextNumIndex = N-1)에서 부터 하나씩 골라서
 중위 순회 결과(arr)에서 해당 숫자가 있는지 탐색하고
 숫자를 발견하면 해당 숫자 기준으로 배열 탐색 범위를 쪼개서
 계속 분할 정복으로 탐색해감
 */
public class Main2263 {

    static int N;
    static int[] arr;   // 중위 순회
    static int[] brr;   // 후위 순회
    static int nextNumIndex;    // brr배열에서 탐색할 숫자 인덱스
    static Node[] nodes;
    static Node root;
    static StringBuilder sb;

    private static class Node {
        int num;
        Node parent;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }

    // 배열 안에 해당 숫자가 있는지 검사
    static int find(int targetNum, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            if (arr[i] == targetNum) return i;
        }
        return -1;
    }

    // arr배열 startIndex~endIndex 범위에서 targetNum를 찾아서 연결하기
    static void solve(int targetNum, int startIndex, int endIndex, int parentNum, boolean isRightChild) {

        // 리프노드인 경우
        if (startIndex == endIndex) return;

        // 배열 안에 해당 숫자가 있는지 검사
        int foundIndex = find(targetNum, startIndex, endIndex);
        if (foundIndex == -1) return;

        nextNumIndex--;

        // 해당 숫자가 있는 경우
        // 새로 노드를 만들어주고
        Node newNode = new Node(targetNum);
        nodes[targetNum] = newNode;

        // 부모 노드와 상호 연결
        if (parentNum == 0) root = newNode;
        else {
            Node parentNode = nodes[parentNum];
            newNode.parent = parentNode;

            if (isRightChild) parentNode.right = newNode;
            else parentNode.left = newNode;
        }

        if (nextNumIndex == -1) return;
        // 오른쪽 탐색
        solve(brr[nextNumIndex], foundIndex, endIndex, targetNum, true);

        if (nextNumIndex == -1) return;
        // 왼쪽 탐색
        solve(brr[nextNumIndex], startIndex, foundIndex, targetNum, false);
    }

    // 전위 순회 출력
    static void preOrder(Node node) {
        if (node == null) return;

        sb.append(node.num + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2263.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        brr = new int[N];
        nodes = new Node[N + 1];
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            brr[i] = n;
        }

        nextNumIndex = N - 1;
        solve(brr[nextNumIndex], 0, N - 1, 0, true);

        preOrder(root);

        System.out.println(sb);
    }
}
