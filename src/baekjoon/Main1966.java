package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1966 {
    static int N;
    static int target;
    static Queue<Node> queue;
    static int[] count;

    private static class Node {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

    }

    public static int findMax() {
        int max_idx = 1;
        for (int i = 1; i < 10; i++) {
            if (count[i] > 0) {
                max_idx = i;
            }
        }
        return max_idx;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1966.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            queue = new LinkedList<>();
            count = new int[10];
            for (int i = 0; i < N; i++) {
                int cost = Integer.parseInt(st.nextToken());
                count[cost]++;
                queue.add(new Node(i, cost));
            }

            int total = 1;
            while (true) {
                Node node = queue.poll();
                if (node.cost == findMax()) {
                    if (node.index == target) break;
                    else {
                        total++;
                        count[node.cost]--;
                    }
                } else {
                    queue.add(node);

                }
            }
            System.out.println(total);
        }
    }
}
