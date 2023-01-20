package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 어떤 지점까지 가는데 걸리는 시간은 bfs로 구할 수 있는데
 가는데 필요한 경우의 수를 어떻게 저장ㅎ라 지가 관건이었음
 따로 경우의 수를 세는 배열(visited_count)을 하나 만들어서
 해당 지점에 처음 도착했을 때와, 이미 도착한 경우를 분기해서 처리함
 */
public class Main12851 {

    final static int limit = 150001;
    static int N;
    static int K;

    static boolean isIn(int n) {
        if (n >= 0 && n < limit) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/12851.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] visited = new int[limit];         // 해당 칸 이미 도착 여부 확인 겸 해당 칸까지 오는데 걸리는 최소 시간
        int[] visited_count = new int[limit];   // 해당 칸까지 최소 시간으로 가는 경우의 수

        Queue<Integer> queue = new LinkedList();
        queue.add(N);
        visited_count[N] = 1;
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int n = queue.poll();

            // K에 도착했을 때
            if (n == K) break;


            int n1 = n - 1;
            int n2 = n + 1;
            int n3 = 2 * n;

            if (isIn(n1)) {
                // 해당 칸에 최초로 도착한 경우
                if (visited[n1] == 0) {
                    queue.add(n1);
                    visited[n1] = visited[n] + 1;
                    visited_count[n1] += visited_count[n];
                }
                // 해당 칸에 이미 도착한 적이 있는데,
                // 해당 칸까지 오는데 걸리는 시간이 이미 도착한 적이 있는 경우의 시간과 같은 경우
                else if (visited[n1] == visited[n] + 1) {
                    visited_count[n1] += visited_count[n];
                }
            }

            if (isIn(n2)) {
                if (visited[n2] == 0) {
                    queue.add(n2);
                    visited[n2] = visited[n] + 1;
                    visited_count[n2] += visited_count[n];
                } else if (visited[n2] == visited[n] + 1) {
                    visited_count[n2] += visited_count[n];
                }
            }

            if (isIn(n3)) {
                if (visited[n3] == 0) {
                    queue.add(n3);
                    visited[n3] = visited[n] + 1;
                    visited_count[n3] += visited_count[n];
                } else if (visited[n3] == visited[n] + 1) {
                    visited_count[n3] += visited_count[n];
                }
            }
        }

        System.out.println(visited[K] - 1 + "\n" + visited_count[K]);
    }
}
