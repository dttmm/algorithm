package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 처음에는 지울 노드의 자식들을 clear해줬는데
 편향트리인 경우를 고려 못해서 틀림
 그래서 그냥 부모 노드의 자식리스트에서 지울 노드를 지워주었음

 아뉘
 루트 노드가 여러개 있을 줄 알고 대비해서 코드 짰는데
 루트 노드는 무조건 하나인가보네..
 */
public class Main1068 {

    static int N;
    static Node[] nodes;
    static int target;
    static int answer;
    static List<Node> rootList;

    private static class Node {
        int num;
        int parentNum;
        List<Node> childList = new ArrayList();

        public Node(int num) {
            this.num = num;
        }
    }

    public static void solve(Node node) {
        // 리프노드인 경우
        if (node.childList.size() == 0) {
            answer++;
            return;
        }

        for (Node child : node.childList) {
            solve(child);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1068.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];
        rootList = new ArrayList();
        answer = 0;

        // 나보다 인덱스 높은 부모를 가질 수 있어서 Null 오류 예방
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(i);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parentNum = Integer.parseInt(st.nextToken());

            nodes[i].parentNum = parentNum;

            // 루트 노드는 따로 저장
            if (parentNum == -1) {
                rootList.add(nodes[i]);
                continue;
            }

            nodes[parentNum].childList.add(nodes[i]);
        }

        target = Integer.parseInt(br.readLine());
        int parentNum = nodes[target].parentNum;

        // 루트 노드인 경우 -> 부모 노드가 없음
        if (parentNum == -1) {
            rootList.remove(nodes[target]);
        }
        // 루트 노드 아닌 경우 -> 부모 노드의 자식리스트에서 지울 노드 remove
        else {
            nodes[parentNum].childList.remove(nodes[target]);
        }

        // 루트 노드에서부터 탐색 시작
        for (Node root : rootList) {
            solve(root);
        }

        System.out.println(answer);
    }
}
