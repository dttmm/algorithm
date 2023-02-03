package softeer.lv3;

import java.util.*;
import java.io.*;

/**
 처음에는 플로이드 와샬로 풀었는데
 어라?
 생각해보니까 최소거리 구하는 것이 아니라
 그냥 다른 노드들까지의 거리 구하는 거였네
 어떤 노드에서 다른 노드까지의 거리는 무조건 하나밖에 없으니까

 그냥 bfs 돌면서 모든 노드에 대해서
 모든 노드까지의 거리 구했는데
 2*10^5 * 2*10^5 = 40억으로 시간초과...

 노드 1에서부터 노드 7까지의 거리를 구한 경우
 노드 7에서부터 노드 1까지의 거리를 구할 필요는 없으니까
 이런 중복을 어떻게 없앨까 생각하다가

 풀이 영상봄
 영상에서 아이디어 얻어서 풀었음
 아이디어 떠올리기 너무 어렵다
 */
public class Main_007_거리_합_구하기 {

    public class Main {
        static int N;
        static List<Node>[] lists;
        static long[] d;
        static int[] count;
        static boolean[] visited;

        private static class Node {
            int num;
            long cost;

            public Node(int num, long cost) {
                this.num = num;
                this.cost = cost;
            }
        }

        // 1노드에서 부터 다른 노드까지 거리 총합 d[1] 구하고
        // 각 노드가 가지고 있는 자식들 개수count[] 구하기
        static int setCount(int start, long sum) {
            int childCount = 1; // 자신 포함 자식들 개수

            // 주변 노드 탐색
            for (Node node : lists[start]) {
                if (visited[node.num]) continue;
                visited[node.num] = true;

                childCount += setCount(node.num, sum + node.cost);
            }

            count[start] = childCount;
            d[1] += sum;
            return childCount;
        }

        // 각 노드들에서 다른 노드까지의 거리 합 d 구하기
        static void solve() {
            visited = new boolean[N + 1];
            visited[1] = true;

            Queue<Integer> queue = new LinkedList();
            queue.add(1);
            while (!queue.isEmpty()) {
                int v = queue.poll();

                for (Node node : lists[v]) {
                    if (visited[node.num]) continue;
                    visited[node.num] = true;

                    d[node.num] = d[v] + ((N - count[node.num] * 2) * node.cost);
                    queue.add(node.num);
                }
            }
        }

        public static void main(String args[]) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            N = Integer.parseInt(br.readLine());
            lists = new ArrayList[N + 1];
            d = new long[N + 1];
            count = new int[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i <= N; i++) {
                lists[i] = new ArrayList();
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                lists[n1].add(new Node(n2, cost));
                lists[n2].add(new Node(n1, cost));
            }

            visited[1] = true;
            setCount(1, 0);

            solve();

            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(d[i] + "\n");
            }

            System.out.println(sb);
        }
    }
}
