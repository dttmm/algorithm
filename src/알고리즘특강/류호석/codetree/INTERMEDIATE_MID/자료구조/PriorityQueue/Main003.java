package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main003 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                if (pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll() + "\n");
            } else pq.add(n);

        }

        bw.flush();
        bw.close();
    }
}

