package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 1, 2, 3번째 대회의 정보를 nodes 배열의 0,1,2 인덱스에 저장하고
 저장된 정보를 토대로 최종 점수에 대한 정보도 nodes 배열 3인덱스에 저장함
 각 대회+최종 점수에 대한 pq도 배열로 만들어서 각각 pq에 넣었고
 pq에서 하나씩 꺼내면서 nodes에 rank정보 업데이트 시키고
 마지막에 순차적으로 nodes돌면서 한꺼번에 rank정보 출력
 */
public class Main_002_성적_평가 {

    public class Main {

        static int N;
        static Node[][] nodes;
        static PriorityQueue<Node>[] pqs;

        private static class Node implements Comparable<Node> {
            int score;
            int rank;

            public Node(int score) {
                this.score = score;
            }

            public int compareTo(Node o) {
                return -(this.score - o.score);
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            N = Integer.parseInt(br.readLine());
            nodes = new Node[4][N];
            pqs = new PriorityQueue[4];

            for (int i = 0; i < 4; i++) {
                pqs[i] = new PriorityQueue();
            }

            // 1,2,3번째 대회 정보 저장
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int score = Integer.parseInt(st.nextToken());
                    Node newNode = new Node(score);
                    nodes[i][j] = newNode;
                    pqs[i].add(newNode);
                }
            }

            // 최종 점수 정보 저장
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int i = 0; i < 3; i++) {
                    sum += nodes[i][j].score;
                }
                Node newNode = new Node(sum);
                nodes[3][j] = newNode;
                pqs[3].add(newNode);
            }

            // 1,2,3,최종까지의 모든 순위 계산해서 넣기
            for (int i = 0; i < 4; i++) {
                int rank = 1;
                int preScore = -1;
                int count = 0;
                while (!pqs[i].isEmpty()) {
                    Node node = pqs[i].poll();

                    if (preScore == -1) {
                        node.rank = rank;
                        preScore = node.score;
                        continue;
                    }

                    if (node.score == preScore) {
                        count++;
                        node.rank = rank;
                    } else {
                        rank++;
                        rank += count;
                        count = 0;
                        node.rank = rank;
                    }

                    preScore = node.score;
                }
            }

            StringBuilder sb = new StringBuilder();
            // 결과 출력
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(nodes[i][j].rank + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}
