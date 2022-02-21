package algorithm.day11dividebacktracking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 17분 40초
public class Solution1247 {
    static Node[] arr;
    static boolean[] visited;
    static int N;
    static Node[] tr;
    static int min;


    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void p(int k) {
        if (k == N) {
            int sum = 0;
            for (int i = 0; i < N + 1; i++) {
                sum += Math.abs(tr[i].x - tr[i + 1].x) + Math.abs(tr[i].y - tr[i + 1].y);
            }
            min = Math.min(sum, min);
        } else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    tr[k + 1] = arr[i];
                    p(k + 1);
                    visited[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day11_1247.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(br.readLine());
            arr = new Node[N + 2];
            visited = new boolean[N + 1];
            tr = new Node[N + 2];
            min = Integer.MAX_VALUE;

            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[0] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            arr[N + 1] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            tr[0] = arr[0];
            tr[N + 1] = arr[N + 1];

            for (int i = 1; i <= N; i++) {
                arr[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            p(0);
            System.out.println("#" + test_case + " " + min);
        }
    }
}