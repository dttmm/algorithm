package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16928 {

    static int N;
    static int M;
    static int[] arr;
    static boolean[] visited;

    public static int solve(int start, int round) {

        Queue<Integer> queue_start = new LinkedList();
        Queue<Integer> queue_round = new LinkedList();

        queue_start.add(start);
        queue_round.add(round);

        while (!queue_start.isEmpty()) {
            start = queue_start.poll();
            round = queue_round.poll();

            for (int i = 1; i <= 6; i++) {
                int destination = start + i;
                if (arr[destination] != 0) destination = arr[destination];

                if (destination == 100) return round;

                if (!visited[destination]) {
                    queue_start.add(destination);
                    queue_round.add(round + 1);
                    visited[destination] = true;
                }
            }
        }
        return round;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/16928.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[101];
        visited = new boolean[101];

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from] = to;
        }

        int result = solve(1, 1);
        System.out.println(result);
    }
}
