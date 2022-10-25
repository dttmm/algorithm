package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.PriorityQueue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main001 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push": {
                    int n = Integer.parseInt(st.nextToken()) * -1;
                    pq.add(n);
                    break;
                }
                case "pop": {
                    bw.write(pq.poll() * -1 + "\n");
                    break;
                }
                case "size": {
                    bw.write(pq.size() + "\n");
                    break;
                }
                case "empty": {
                    if (pq.isEmpty()) {
                        bw.write("1\n");
                    } else {
                        bw.write("0\n");
                    }
                    break;
                }
                case "top": {
                    bw.write(pq.peek() * -1 + "\n");
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}

