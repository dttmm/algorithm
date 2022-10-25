package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main002 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.add(n * -1);
        }

        for (int i = 0; i < M; i++) {
            int n = pq.poll() * -1;
            n -= 1;
            pq.add(n * -1);
        }

        int answer = pq.poll() * -1;
        System.out.println(answer);
    }
}

