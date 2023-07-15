package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 설계 8분 구현 11분 디버깅 1분
 이분탐색으로 풀었을 때 재채점 결과 틀려서
 트리맵으로 다시 풀음

 어떻게 하면 정답을 구할 수 있을까 생각해봄
 가치가 가장 높을 것을 먼저 넣어가야
 최대값을 구할 수 있으니까
 가치가 제일 높은 것부터 하나 골라서
 넣을 수 있는 가방에 넣음
 가방에 넣을 때는 보석의 무게와 같거나 큰 가방을 선택함
 이 과정에서 트리맵 사용
 가방의 무게가 중복 될 수 있으니 set 대신 map 사용

 음..
 왜 이전에 풀 때는
 왜 트리 자료구조를 생각 못했을까..

 틀림
 보석을 가방에 매칭 시킬 때
 map에 남아있는 가방의 개수가 1개이면
 map에서 해당 가방을 삭제하고
 가방의 개수가 2개 이상 남아있다면
 가방의 개수를 하나 줄이는 분기처리를 안했음
 */

public class Main1202_2 {

    static int N;
    static int K;
    static Node[] nodes;
    static TreeMap<Integer, Integer> bag;

    private static class Node implements Comparable<Node> {
        int m;
        int v;

        public Node(int m, int v) {
            this.m = m;
            this.v = v;
        }

        // 가치 높은 순, 무게 높은 순
        @Override
        public int compareTo(Node o) {
            if (this.v != o.v) return -(this.v - o.v);
            return -(this.m - o.m);
        }
    }

    // 가방에 보석 넣기
    static int solve(Node node) {
        // 보석 무게보다 같거나 큰 가방 찾기
        Integer n = bag.ceilingKey(node.m);

        // 넣을 수 있는 가방이 없는 경우
        if (n == null) return -1;

        // 가방이 하나 남은 경우
        if (bag.get(n) == 1) bag.remove(n);
        // 가방이 두개 이상 남은 경우
        else bag.put(n, bag.get(n) - 1);

        return node.v;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1202_2.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new Node[N];
        bag = new TreeMap();

        // 보석 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            Node newNode = new Node(m, v);
            nodes[i] = newNode;
        }

        // 가방 입력 받기
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(br.readLine());

            if (bag.get(n) != null) bag.put(n, bag.get(n) + 1);
            else bag.put(n, 1);
        }

        // 보석 정렬
        Arrays.sort(nodes);

        // 보석 넣을 수 있는 가방에 보석 넣기
        long answer = 0;
        for (int i = 0; i < nodes.length; i++) {
            int result = solve(nodes[i]);
            if (result == -1) continue;

            answer += result;
        }

        System.out.println(answer);
    }
}
