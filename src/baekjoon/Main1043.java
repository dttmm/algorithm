package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1043 {

    static int N;
    static int M;
    static boolean[] visited;           // 최종적으로 진실을 알고있는 사람
    static Queue<Integer> knowns;       // 처음에 진실을 알고있는 사람
    static Input[] inputs;              // 받은 입력값 저장
    static LinkedList<Integer>[] nodes; // 사람들을 연결할 그래프

    // 입력값을 저장할 클래스
    private static class Input {
        int n;
        int[] people;

        public Input(int n, int[] people) {
            this.n = n;
            this.people = people;
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int n = queue.poll();
            LinkedList<Integer> list = nodes[n];
            for (int u : list) {
                if (!visited[u]) {
                    visited[u] = true;
                    queue.add(u);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1043.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        knowns = new LinkedList();
        inputs = new Input[M];
        nodes = new LinkedList[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new LinkedList();
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        // 처음에 알고있는 사람 저장
        for (int i = 0; i < k; i++) {
            int n = Integer.parseInt(st.nextToken());
            knowns.add(n);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] people = new int[n];

            // 앞 뒤 사람을 서로 연결
            int pre = 0;
            for (int j = 0; j < n; j++) {
                int personNum = Integer.parseInt(st.nextToken());
                people[j] = personNum;

                if (pre != 0) {
                    nodes[pre].add(personNum);
                    nodes[personNum].add(pre);
                }
                pre = personNum;
            }

            // 입력값을 저장해줌
            Input newInput = new Input(n, people);
            inputs[i] = newInput;
        }

        // 처음에 알고있는 사람 중심으로 bfs
        // -> 진실을 알게 되는 사람이 전파됨
        visited = new boolean[N + 1];
        while (!knowns.isEmpty()) {
            bfs(knowns.poll());
        }

        // 입력값을 다시 확인하면서 진실을 알고있는 사람 체크
        int total = M;
        for (int i = 0; i < M; i++) {
            Input input = inputs[i];
            boolean flag = false;

            for (int j = 0; j < input.n; j++) {
                int n = input.people[j];
                if (visited[n]) {
                    flag = true;
                    break;
                }
            }

            // 해당 무리중에 진실을 알고 있는 사람이 있다면
            if (flag) total--;
        }

        System.out.println(total);
    }
}
