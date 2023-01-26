package baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 벨만 포드 공부함
 INF값을 그냥 편하게 long으로 해야겠다
 int로 하니까 터지네
 */
public class Main11657 {

    final static long INF = Long.MAX_VALUE;
    static int N;
    static int M;
    static List<Edge> lists;
    static long[] d;

    private static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11657.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lists = new ArrayList();
        d = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            d[i] = INF;
        }
        d[1] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            lists.add(new Edge(from, to, cost));
        }

        boolean flag = true;    // 무한 순환 플래그 true: 무한 순환 아님
        for (int i = 1; i <= N; i++) {
            for (Edge edge : lists) {
                if (d[edge.from] == INF) continue;

                long newD = d[edge.from] + edge.cost;
                if (newD < d[edge.to]) {
                    d[edge.to] = newD;

                    if (i != N) continue;
                    flag = false;
                    break;
                }
            }
        }

        if (!flag) {
            bw.write("-1");
        } else {
            for (int i = 2; i <= N; i++) {
                if (d[i] == INF) bw.write("-1\n");
                else bw.write(d[i] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }
}
