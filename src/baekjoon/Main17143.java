package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17143 {

    static int N;
    static int M;
    static int Q;
    static Node[][] arr;
    static int[] di = {0, -1, 1, 0};
    static int[] dj = {-1, 0, 0, 1};
    static int curMan;
    static int answer;

    static void moveMan() {
        if (curMan == M) return;

        for (int i = 0; i < N; i++) {
            if (arr[i][curMan] == null) continue;

            catchShark(i, curMan);
            break;
        }
    }

    static void catchShark(int i, int j) {
        answer += arr[i][j].z;
        arr[i][j] = null;
    }

    static void moveShark() {
        Node[][] brr = new Node[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Node node = arr[i][j];
                if (node == null) continue;

                if (node.d == 1 || node.d == 2) {
                    int total = node.i + di[node.d] * node.s;
                    if (total < 0) {
                        int m = (total * -1) / N;
                        total += (total * -1) * (m + 1);
                    }
                    int newI = total % (2 * N);
                    if (total >= N) {
                        newI = newI - N;
                        node.d = 3 - node.d;
                    }

                    node.i = newI;
                    if (brr[newI][node.j] != null) brr[newI][node.j] = eatShark(brr[newI][node.j], node);
                    else brr[newI][node.j] = node;
                } else {
                    int total = node.j + dj[node.d] * node.s;
                    if (total < 0) {
                        int m = (total * -1) / M;
                        total += (total * -1) * (m + 1);
                    }
                    int newJ = total % (2 * M);
                    if (total >= M) {
                        newJ = newJ - M;
                        node.d = 3 - node.d;
                    }

                    node.j = newJ;
                    if (brr[node.i][newJ] != null) brr[node.i][newJ] = eatShark(brr[node.i][newJ], node);
                    else brr[node.i][newJ] = node;
                }
            }
        }

        arr = copyArr(arr, brr);
    }

    static Node[][] copyArr(Node[][] arr, Node[][] brr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = brr[i][j];
            }
        }
        return arr;
    }

    static Node eatShark(Node n1, Node n2) {
        if (n1.z > n2.z) return n1;
        else return n2;
    }

    private static class Node {
        int i;
        int j;
        int s;
        int d;
        int z;

        public Node(int i, int j, int s, int d, int z) {
            this.i = i;
            this.j = j;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17143.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new Node[N][M];
        curMan = -1;
        answer = 0;

        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            i--;
            j--;
            d %= 4;
            Node newNode = new Node(i, j, s, d, z);
            arr[i][j] = newNode;
        }

        while (curMan < M) {
            curMan++;
            moveMan();
            moveShark();
        }

        System.out.println(answer);
    }
}
