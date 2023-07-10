package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 설계 13분 구현 7분
 TreeSet 이요하여 풀음
 내 기준으로 왼쪽에서 나보다 큰 놈을 찾아야 하는데
 왼쪽에서부터 탐색하면서 찾으려면 완탐을 하는 방법밖에 없음
 자신보다 큰 놈을 만나지 않은 탑들을 set에 넣어 보관하고
 오른쪽에서부터 탐색 시작하면서
 set에 자신보다 작은 탑들 찾아서
 해당 탑에서 발사한 레이저를 수신하는 놈을 자신으로 설정해줌

 문제 유형 보니까 스택이던데
 곰곰이 생각해보니까 굳이 TreeSet안써도
 스택으로도 자신보다 큰 놈을 만나지 않은 탑들을 관리할 수 있겠군하
 */
public class Main2493 {

    static int N;
    static Node[] nodes;

    private static class Node implements Comparable<Node> {
        int num;
        int index;
        int receiveTop; // 탑에서 발사한 레이저 신호를 수신하는 탑

        public Node(int num, int index) {
            this.num = num;
            this.index = index;
            this.receiveTop = 0;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }

    // 탑에서 발사한 레이저 신호를 수신하는 탑 구하기
    static void solve() {
        TreeSet<Node> set = new TreeSet();       // 자신보다 큰 놈을 만나지 않은 탑 저장
        List<Node> deleteList = new ArrayList(); // 자신보다 큰 놈을 만난(TreeSet에서 삭제할) 탑 저장
        for (int i = nodes.length - 1; i > 0; i--) {
            deleteList.clear();
            Node node = nodes[i];
            for (Node setNode : set) {
                // 현재 node보다 낮은 탑인 경우
                if (setNode.num <= node.num) {
                    setNode.receiveTop = node.index;
                    deleteList.add(setNode);
                } else break;
            }

            // TreeSet에서 자신보다 큰 놈을 만난 탑 삭제
            for (int j = 0; j < deleteList.size(); j++) {
                set.remove(deleteList.get(j));
            }

            set.add(node);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2493.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];

        // 입력 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(n, i);
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(nodes[i].receiveTop + " ");
        }

        System.out.println(sb);
    }
}
