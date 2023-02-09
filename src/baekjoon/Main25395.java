package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 처음에는 treeset과 bfs를 이용하여
 * 노드를 하나씩 꺼내면서
 * 해당 노드 범위 안에 있는 노드들을 treeset에서 꺼내서
 * 해당 노드들을 bfs 큐에 추가해주는 방식으로 풀었음
 * 근데 시간초과
 * <p>
 * 1시간 동안 다른 아이디어가 안떠올라서 문제유형 봄
 * 투 포인터길래
 * 범위로 가능한 최소값과 최대값을 정하고
 * 해당 범위 안에서 계속 최소값(min)과 최대값(max)을 갱신 시키는 방법을 떠올림
 * 우선 입력받은 노드를 해당 노드의 위치인 position을 기준으로 정렬을 해주고
 * 자신의 왼쪽노드를 계속 탐색하고
 * 오른쪽 노드도 계속 탐색하고
 * 왼쪽 오른쪽 둘다 탐색을 했는데
 * min 또는 max값이 갱신이 안되었다면
 * 더이산 해당 범위 안에는 검사할만한 새로운 노드가 없다는 뜻이므로 탐색 종료함
 * <p>
 * +
 * position만으로 compareTo를 설정하면
 * treeSet에 노드를 넣을 때
 * position이 겹치면 동일한 노드로 간주되서
 * position이 동일한 경우도 예외처리 해주었었음...
 */
public class Main25395 {

    static int N;
    static int S;
    static Node[] nodes;
    static List<Integer> answerList;
    static Node startNode;

    private static class Node implements Comparable<Node> {
        int index;
        int position;
        int fuel;
        Node leftNode;
        Node rightNode;

        public Node(int index, int position, int fuel) {
            this.index = index;
            this.position = position;
            this.fuel = fuel;
        }

        @Override
        public int compareTo(Node o) {
            return this.position - o.position;
        }
    }

    static void solve() {
        Node currentLeftNode = startNode.leftNode;
        Node currentRightNode = startNode.rightNode;
        answerList.add(startNode.index);
        int min = startNode.position - startNode.fuel;
        int max = startNode.position + startNode.fuel;

        boolean flag = true;    // 갱신 되었는지 확인 플래그
        while (flag) {
            flag = false;

            while (currentLeftNode != null && currentLeftNode.position >= min) {
                answerList.add(currentLeftNode.index);
                min = Math.min(min, currentLeftNode.position - currentLeftNode.fuel);
                max = Math.max(max, currentLeftNode.position + currentLeftNode.fuel);
                currentLeftNode = currentLeftNode.leftNode;

                flag = true;
            }

            while (currentRightNode != null && currentRightNode.position <= max) {
                answerList.add(currentRightNode.index);
                min = Math.min(min, currentRightNode.position - currentRightNode.fuel);
                max = Math.max(max, currentRightNode.position + currentRightNode.fuel);
                currentRightNode = currentRightNode.rightNode;

                flag = true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/25395.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        answerList = new ArrayList();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            Node newNode = new Node(i + 1, n, 0);
            nodes[i] = newNode;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            nodes[i].fuel = n;
        }

        startNode = nodes[S - 1];

        // position 기준으로 노드 정렬하고
        // 링크드 리스트로 연결
        Arrays.sort(nodes);
        for (int i = 0; i < N; i++) {
            if (i != 0) nodes[i].leftNode = nodes[i - 1];
            if (i != N - 1) nodes[i].rightNode = nodes[i + 1];
        }

        solve();

        Collections.sort(answerList);
        StringBuilder sb = new StringBuilder();
        for (int index : answerList) {
            sb.append(index + " ");
        }
        System.out.println(sb);
    }
}
