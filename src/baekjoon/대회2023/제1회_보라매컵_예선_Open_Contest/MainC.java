package baekjoon.대회2023.제1회_보라매컵_예선_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MainC {

    private static class Node implements Comparable<Node> {
        int deadline;
        int work;

        public Node(int deadline, int work) {
            this.deadline = deadline;
            this.work = work;
        }

        @Override
        public int compareTo(Node o) {
            return this.deadline - o.deadline;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_보라매컵_예선_Open_Contest/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Node> pq = new PriorityQueue();

        int answer = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int work = Integer.parseInt(st.nextToken());

            pq.add(new Node(deadline, work));
        }

        int work_weekdays = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int limit = node.deadline;
            limit -= (node.deadline / 7) * 2;
            if (node.deadline % 7 == 6) limit--;

            work_weekdays += node.work;
            if (work_weekdays > limit) {
                answer += work_weekdays - limit;
                work_weekdays = limit;
            }

            if (answer > node.deadline) {
                answer = -1;
                break;
            }
        }

        System.out.println(answer);
    }
}