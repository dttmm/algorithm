package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main19941 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/19941.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> hPQ = new PriorityQueue();
        PriorityQueue<Integer> pPQ = new PriorityQueue();

        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'H') {
                hPQ.add(i);
            } else {
                pPQ.add(i);
            }
        }

        int sum = 0;
        while (!hPQ.isEmpty() && !pPQ.isEmpty()) {
            int h = hPQ.peek();
            int p = pPQ.peek();

            if (Math.abs(h - p) <= K) {
                sum++;
                hPQ.poll();
                pPQ.poll();
            } else {
                if (h < p) {
                    hPQ.poll();
                } else {
                    pPQ.poll();
                }
            }
        }
        System.out.println(sum);
    }
}
