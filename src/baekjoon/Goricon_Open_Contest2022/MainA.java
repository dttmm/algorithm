package baekjoon.Goricon_Open_Contest2022;

import java.io.*;
import java.util.StringTokenizer;

public class MainA {

    private static class Node {
        int k;
        int d;
        int a;

        public Node(int k, int d, int a) {
            this.k = k;
            this.d = d;
            this.a = a;
        }

        public long cal(long nK, long nD, long nA) {
            long sum = this.k * nK - this.d * nD + this.a * nA;
            return sum < 0 ? 0 : sum;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/Goricon_Open_Contest2022/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int M = Integer.parseInt(br.readLine());

            Node[] nodes = new Node[M];
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int K = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());
                int A = Integer.parseInt(st.nextToken());

                Node newNode = new Node(K, D, A);
                nodes[i] = newNode;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int nK = Integer.parseInt(st.nextToken());
            int nD = Integer.parseInt(st.nextToken());
            int nA = Integer.parseInt(st.nextToken());

            long sum = 0;
            for (int i = 0; i < M; i++) {
                sum += nodes[i].cal(nK, nD, nA);
            }

            bw.write(sum + "\n");
        }
        bw.flush();
        bw.close();
    }
}
