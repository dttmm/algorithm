package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 비교해야할 변수가 많으므로 Node클래스를 만들어서 관리함
 조합에 백트래킹 적용함
 조합을 이용해서 하나씩 뽑은다음
 뽑을 때마다 4가지 영양소를 만족하는지 확인한 후,
 비용이 더 낮은 경우에는 답을 갱신
 비용이 더 높은 경우에는 더 볼것도 없으므로 return함
 */
public class Main19942 {

    static int N;
    static Node[] arr;
    static Node standard;
    static Node answerNode;

    private static class Node {
        int p;
        int f;
        int s;
        int v;
        int cost;
        List<Integer> nodeList = new ArrayList();

        public Node(int p, int f, int s, int v, int cost) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.cost = cost;
        }

        // 4가지 영양소를 만족하는지 확인
        public boolean isValid(Node sum) {
            if (sum.p >= this.p && sum.f >= this.f && sum.s >= this.s && sum.v >= this.v) return true;
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(cost + "\n");
            for (int i : nodeList) {
                sb.append(i + " ");
            }
            return sb.toString();
        }
    }

    static void c(int k, int start, Node sum) {
        if (k > N) return;

        // 4가지 영양소 만족하는지 확인
        if (k != 0 && standard.isValid(sum)) {
            // 영양소 만족했는데 비용이 더 높은경우는 더이상 볼 필요 없음
            if (sum.cost >= answerNode.cost) return;

            // 비용이 더 낮은 경우에만 갱신
            answerNode.cost = sum.cost;
            answerNode.nodeList = sum.nodeList;
        }

        for (int i = start; i < N; i++) {
            Node pick = arr[i];

            // 넘겨받은 sum노드에 뽑은 노드 정보 더해서 다시 조합 돌림
            Node newNode = new Node(sum.p + pick.p, sum.f + pick.f, sum.s + pick.s, sum.v + pick.v, sum.cost + pick.cost);
            newNode.nodeList.addAll(sum.nodeList);
            newNode.nodeList.add(i + 1);
            c(k + 1, i + 1, newNode);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/19942.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        standard = new Node(p, f, s, v, 0);

        arr = new Node[N];
        answerNode = new Node(0, 0, 0, 0, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            p = Integer.parseInt(st.nextToken());
            f = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr[i] = new Node(p, f, s, v, cost);
        }

        c(0, 0, new Node(0, 0, 0, 0, 0));

        if (answerNode.nodeList.size() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(answerNode);
        }
    }
}
