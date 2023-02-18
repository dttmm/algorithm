package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 메모리 제한이 너무 적은것을 보니 완탐은 안될 것으로 판단
 결국 N번만 탐색하라는 소리 같았음

 일단 마지막 행에 있는 데이터들 pq에 넣어주고
 pq에서 큰 숫자부터 뽑으면서
 자기 윗 줄에 있는 데이터 넣어줌
 이 과정을 N번 반복하여 나온 수가 정답
 */
public class Main2075 {

    static int N;
    static int[][] arr;
    static PriorityQueue<Node> pq;

    private static class Node implements Comparable<Node> {
        int i;
        int j;
        int value;

        public Node(int i, int j, int value) {
            this.i = i;
            this.j = j;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return -(this.value - o.value);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2075.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        pq = new PriorityQueue();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i][j] = n;

                // 마지막 행인 경우에만 pq에 미리 넣어둠
                if (i != N - 1) continue;
                pq.add(new Node(i, j, n));
            }
        }

        int count = 1;
        int answer = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            // N번째 큰 수
            if (count == N) {
                answer = node.value;
                break;
            }
            count++;

            // 자기보다 위에 있는 노드 pq에 넣어둠
            if (node.i == 0) continue;
            pq.add(new Node(node.i - 1, node.j, arr[node.i - 1][node.j]));
        }

        System.out.println(answer);
    }
}
