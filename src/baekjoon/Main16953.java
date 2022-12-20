package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 노드 클래스를 만들어서
 숫자와 연산 횟수를 저장함
 연산 횟수가 적은 노드를 불러와서
 2를 곱한 경우와 1를 오른쪽에 추가한 경우에 대해
 숫자의 범위를 벗어나지 않은 경우에는
 해당 연산 결과를 pq애 추가해줌

 방문처리를 해줘야 되는데
 숫자 범위가 10억이 넘어가기 때문에
 배열로는 할 수가 없어서
 set으로 방문처리함
 */
public class Main16953 {

    static int A;
    static int B;
    static Set<Long> set;

    private static class Node implements Comparable<Node> {
        long n;
        int count;

        public Node(long n, int count) {
            this.n = n;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }

    static boolean isIn(long x) {
        if (x >= 1 && x <= 1000000000) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16953.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        set = new HashSet();

        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(A, 0));

        int answer = -2;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (set.contains(node.n)) continue;
            set.add(node.n);

            if (node.n == B) {
                answer = node.count;
                break;
            }

            long n1 = node.n * 2;
            long n2 = node.n * 10 + 1;
            if (isIn(n1)) {
                pq.add(new Node(n1, node.count + 1));
            }
            if (isIn(n2)) {
                pq.add(new Node(n2, node.count + 1));
            }
        }

        System.out.println(answer + 1);
    }
}
