package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 11분 구현 15분
 처음에는 주어진 점수를 순차적으로 탐색하면서
 태수의 등수를 구하려고 했었는데
 동점인 경우, 주어진 점수가 없는 경우 등
 설계를 하다보니
 고려해서 분기해야할 사항이 많아지게됨
 순차 탐색을 통해 분기로 처리하기에는 너무 복잡해서

 pa에 모든 점수를 다 넣고
 하나씩 꺼내면서 태수의 점수가 몇 등인지 확인함
 */
public class Main1205 {

    static int N;
    static int P;
    static PriorityQueue<Node> pq;  // 모든 점수 저장
    static int newScore;    // 태수의 점수

    private static class Node implements Comparable<Node> {
        int score;
        int isNew;  // 새로 들어온 점수인지(태수의 점수)

        public Node(int score, int isNew) {
            this.score = score;
            this.isNew = isNew;
        }

        @Override
        public int compareTo(Node o) {
            // 높은 점수 우선
            if (this.score != o.score) return -(this.score - o.score);
            // 기존에 있던 점수 우선
            return this.isNew - o.isNew;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1205.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        newScore = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue();

        // 입력 받음
        if (N > 0) st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(new Node(n, 0));
        }
        // 태수 점수 추가
        pq.add(new Node(newScore, 1));

        int answer = -1;
        int count = 1;  // pq에서 몇 개 꺼냈는지
        int prev = -1;  // 이전 점수
        int rank = 0;   // 현재 점수의 등수
        int duplicate = 1;  // 동점이 몇 개인지
        while (!pq.isEmpty()) {
            if (count > P) break;

            Node node = pq.poll();
            // 이전 점수와 다른 경우
            if (node.score != prev) {
                prev = node.score;
                rank += duplicate;
                duplicate = 1;
            }
            // 이전 점수와 동점인 경우
            else duplicate++;

            // 태수의 점수인 경우
            if (node.isNew == 1) {
                answer = rank;
                break;
            }

            count++;
        }

        if (answer == 0) System.out.println(-1);
        else System.out.println(answer);
    }
}
