package baekjoon.제1회곰곰컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class MainE {
    static int N;
    static int M;
    static int K;
    static Map<Integer, List<Integer>> map;
    static int[] visited;
    static Stack<Integer> stack;
    static String answer;

    public static void dfs() {
        while (!stack.isEmpty()) {
            boolean flag = true;
            int v = stack.pop();
            visited[v] = 1;
            if (map.get(v) != null) {
                for (int i = 0; i < map.get(v).size(); i++) {
                    int u = map.get(v).get(i);
                    if (visited[u] == 2) {
                        flag = false;
                        continue;
                    } else if (visited[u] == 0) {
                        stack.push(u);
                    }
                }
            } else {
                answer = "yes";
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/제1회곰곰컵/E.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap();
        answer = "Yes";

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (map.get(from) == null) {
                map.put(from, new ArrayList());
            }
            map.get(from).add(to);

        }

        K = Integer.parseInt(br.readLine());
        visited = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 1) {
                System.out.println(answer);
                return;
            }
            visited[n] = 2;
        }

        stack = new Stack();
        stack.push(1);
        dfs();

        System.out.println(answer);
    }
}
