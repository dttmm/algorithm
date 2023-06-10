package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 5분 구현 7분
 간단한 구현 문제
 각 메달 개수대로 정렬하고
 1등 부터 순회하면서
 K국가의 등수 찾음
 */
public class Main8979 {

    static int N;
    static int K;
    static Node[] nodes;

    private static class Node implements Comparable<Node> {
        int num;
        int gold;
        int silver;
        int bronze;

        public Node(int num, int gold, int silver, int bronze) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        // 둘의 메달 개수가 같은지 확인
        public boolean isSame(Node o) {
            return (this.gold == o.gold && this.silver == o.silver && this.bronze == o.bronze);
        }

        @Override
        public int compareTo(Node o) {
            if (this.gold != o.gold) return -(this.gold - o.gold);
            if (this.silver != o.silver) return -(this.silver - o.silver);
            return -(this.bronze - o.bronze);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/8979.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new Node[N];

        // 입력 받음
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(num, gold, silver, bronze);
        }

        // 높은 순으로 정렬
        Arrays.sort(nodes);

        // 탐색하면서 K국가 찾기
        int rank = 1;
        int duplicate = 1;
        for (int i = 0; i < nodes.length; i++) {
            if (i == 0) {
                // K국가 찾은 경우
                if (nodes[i].num == K) break;
                continue;
            }

            // 이전 등수와 메달이 똑같은 경우
            if (nodes[i].isSame(nodes[i - 1])) duplicate++;
            else {
                rank += duplicate;
                duplicate = 1;
            }

            // K국가 찾은 경우
            if (nodes[i].num == K) break;
        }

        System.out.println(rank);
    }
}
