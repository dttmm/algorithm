package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 설계 21분 구현 14분
 세그먼트 트리로 풀어봄
 와 세그먼트 트리로 자신보다 큰 수가 앞에 몇 개 있는지 알 수가 있구나
 신세계로다

 세그먼트 트리로 풀었을 때 1,896ms
 머지소트 특성을 이용해서 풀었을 때 472ms
 */
public class Main1517_2 {

    static int N;
    static Node[] nodes;
    static int[] tree;
    static int treeSize;
    static int startTreeIndex;

    // 인덱스 압축 (숫자를 인덱스에 대응시키기 위함)
    private static class Node implements Comparable<Node> {
        int n;
        int index;

        public Node(int n, int index) {
            this.n = n;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (this.n != o.n) return -(this.n - o.n);
            return -(this.index - o.index);
        }
    }

    // 트리 초기화
    static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        startTreeIndex = treeSize / 2;
        tree = new int[treeSize];
    }

    // start ~ end 구간의 개수 반환
    static int query(int start, int end, int total) {
        if (start > end) return total;

        if (start % 2 == 1) total += tree[start];
        if (end % 2 == 0) total += tree[end];

        start = (start + 1) / 2;
        end = (end - 1) / 2;
        return query(start, end, total);
    }

    // 해당 구간 1증가 시킴
    static void update(int n) {
        if (n == 0) return;

        tree[n]++;
        update(n / 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1517.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        // 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            Node newNode = new Node(n, i);
            nodes[i] = newNode;
        }

        // 큰 수를 우선으로 정렬
        Arrays.sort(nodes);

        // 트리 초기화
        initTree();

        // 나보다 앞에 큰 수가 몇 개 있는지 카운팅
        long answer = 0;
        for (Node node : nodes) {
            int index = node.index + startTreeIndex;    // 해당 숫자가 트리에서 어디에 위치하는지 계산
            answer += query(startTreeIndex, index, 0);
            update(index);
        }

        System.out.println(answer);
    }
}
