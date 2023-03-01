package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 N이 십만이므로 N^2으로는 풀 수가 없구먼유
 문제의 핵심은 두 순위 중 하나라도
 다른 지원자보다 모두 높은 경우는 살리고
 두 순위 모두 다른 지원자보다 낮은 경우만 거르면(?)됨

 일단 두 순위 중에서 한 순위(rank1)를 기준으로 잡고 정렬을 하고
 기준 순위가 높은 사람부터 탐색하면
 뒤에 있는 사람은 무조건 rank1이 앞에 있는 사람보다 순위가 낮으니까
 다른 순위(rank2)를 비교해서
 rank2가 이전 앞에 나왔던 rank2의 최소값(min) <- 그니까 제일 높은 순위
 보다 순위가 낮다면
 두 순위 모두 앞에 사람보다 낮은 것이므로
 해당 사람을 걸렀(?)음

 이게 순위라서 말이 헷갈리네
 순위가 높다 == 실제 가지고 있는 숫자는 낮다

 우왕와오아
 1년전, 뉴비시절에 이 문제 봤을 때는
 문제도 풀이도 이해 안됐었는데..
 많이 발전쓰했네👽
 */
public class Main1946 {

    static int N;
    static Node[] nodes;

    private static class Node implements Comparable<Node> {
        int rank1;
        int rank2;

        public Node(int score1, int score2) {
            this.rank1 = score1;
            this.rank2 = score2;
        }

        @Override
        public int compareTo(Node o) {
            return this.rank1 - o.rank1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1946.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            nodes = new Node[N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int rank1 = Integer.parseInt(st.nextToken());
                int rank2 = Integer.parseInt(st.nextToken());

                nodes[i] = new Node(rank1, rank2);
            }

            Arrays.sort(nodes);

            int min = nodes[0].rank2;
            int answer = 1;
            for (int i = 1; i < N; i++) {
                int rank = nodes[i].rank2;

                if (rank > min) continue;
                min = rank;
                answer++;
            }

            bw.append(answer + "\n");
        }

        bw.flush();
        bw.close();
    }
}
