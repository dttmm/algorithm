package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 설계 6분 구현 6분
 일단 어느 순서로 책을 고르게할지 생각함
 앞 번호 책부터 고르게 할 경우
 1번 책을 원하는 학생이 여러명이면
 1~2를 원하는 학생보다 1~1을 원하는 학생에서 먼저 책을 주었음
 1~2를 원하는 학생은 1번 책을 못 받아도 2번 책을 받을 수 있는 가능성이 생기니까
 a가 작은 순, 그다음 b가 작은 순으로 학생을 뽑게끔 우선순위를 정함

 근데 앞 번호부터 책을 주게 되면
 만약, 1~1, 1~3, 2~2 를 원하는 학생이 있다면
 1~3 학생이 3번 책을 가져야 모두가 최대로 책을 가질 수 있음
 a와 b의 우선순위로만 책을 나누어주게 되면 1~3 학생이 2번 책을 가져가게 됨
 그래서 2~2 학생처럼 책을 받을 수 있는 범위가 적은 학생들부터 책을 나누어줘야됨

 우선순위 대로 학생을 뽑았을 때
 해당 학생의 a번 책이 없을 경우
 a+1번의 책을 받을 수 있는 후보가 되도록
 pq에 a+1을 해서 추가해줌
 그러면 위에서 고려했던 사항들을 모두 만족하게 됨
 */
public class Main9576 {

    static int N;
    static int M;
    static boolean[] book;
    static int count;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int a;
        int b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Node o) {
            if (this.a != o.a) return this.a - o.a;
            return this.b - o.b;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9576.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            book = new boolean[N + 1];
            count = 0;
            pq = new PriorityQueue();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                pq.add(new Node(start, end));
            }

            while (!pq.isEmpty()) {
                Node node = pq.poll();

                if (!book[node.a]) {
                    book[node.a] = true;
                    count++;
                    continue;
                }

                int newStart = node.a + 1;
                if (newStart > node.b) continue;

                pq.add(new Node(newStart, node.b));
            }

            sb.append(count + "\n");
        }
        System.out.println(sb);
    }
}
