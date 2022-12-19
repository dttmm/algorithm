package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 N에서 K까지 가는 경우와
 N+K에서 K까지 가는 경우
 만약 둘다 1만큼씩 이동해서 가는 경우
 걸리는 시간은 N으로 똑같고
 해당 경우가 최대 걸리는 시간이자 경계값이라 판단하여
 d의 크기를 200000으로 설정함

 걸리는 시간이 가장 짧은 노드를 뽑아서
 -1칸, 1칸, 2배 움직여가며
 범위 안에 들어있는 경우에 우선순위 큐에 추가를 해주었음

 그러면 자연스럽게 K값이 나오게 되는 경우, 해당 경우가 정답이 됨
 걸리는 시간이 짧은 것부터 검사하니까
 K값이 최초로 나오게 된 경우보다 더 짧은 시간은 없음

 근데 걸리는 시간인데 나 왜 distance라고 선언했쥐...?
 */
public class Main13549 {

    static int N;
    static int K;
    static int[] d;
    static boolean[] visited;

    private static class Node implements Comparable<Node> {
        int x;
        int distance;

        public Node(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    static boolean isIn(int x) {
        if (x >= 0 && x < 200000) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/13549.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        d = new int[200000];
        visited = new boolean[200000];
        int answer = 0;

        PriorityQueue<Node> pq = new PriorityQueue();
        pq.add(new Node(N, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.x == K) {
                answer = node.distance;
                break;
            }

            if (visited[node.x]) continue;
            visited[node.x] = true;

            int n1 = node.x - 1;
            int n2 = node.x + 1;
            int n3 = node.x * 2;

            if (isIn(n1)) {
                pq.add(new Node(n1, node.distance + 1));
            }
            if (isIn(n2)) {
                pq.add(new Node(n2, node.distance + 1));
            }
            if (isIn(n3)) {
                pq.add(new Node(n3, node.distance));
            }
        }

        System.out.println(answer);
    }
}
