package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main11866 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11866.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        bw.write('<');
        int k = 1;
        while (!queue.isEmpty()) {
            int n = queue.poll();
            if (k != K) {
                queue.add(n);
            } else {
                if (!queue.isEmpty()) {
                    bw.write(n + ", ");
                } else {
                    bw.write(n + "");
                }
                k = 0;
            }
            k++;
        }
        bw.write('>');

        bw.flush();
        bw.close();
    }
}
