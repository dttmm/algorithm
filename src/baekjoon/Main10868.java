package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 세그먼트트리 응용한 문제 풀어보고 싶었음
 구간합 구할 때와 프로세스는 비슷하고
 구간합 대신 최솟값 갱신시켜주면 끝
 */
public class Main10868 {

    static int N;
    static int M;
    static int[] arr;
    static int[] tree;

    // 트리 초기화
    static void initTree() {
        // 트리 만들기
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeCount = (int) Math.pow(2, h + 1);
        tree = new int[treeCount];
        for (int i = 1; i < treeCount; i++) {
            tree[i] = Integer.MAX_VALUE;
        }

        // 리프노드 초기화
        int startIndex = treeCount / 2;
        for (int i = 0; i < N; i++) {
            tree[startIndex + i] = arr[i];
        }

        // 트리 초기화
        for (int i = treeCount - 1; i > 1; i -= 2) {
            int n1 = tree[i];
            int n2 = tree[i - 1];

            int parentIndex = i / 2;
            tree[parentIndex] = Math.min(n1, n2);
        }
    }

    // 최솟값 구하기
    static int getMin(int start, int end, int min) {
        if (start > end) return min;

        if (start % 2 == 1) min = Math.min(min, tree[start]);
        if (end % 2 == 0) min = Math.min(min, tree[end]);

        start = (start + 1) / 2;
        end = (end - 1) / 2;

        return getMin(start, end, min);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10868.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        initTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            // 구간을 트리의 인덱스로 변환
            int start = n1 - 1 + tree.length / 2;
            int end = n2 - 1 + tree.length / 2;
            int result = getMin(start, end, Integer.MAX_VALUE);

            sb.append(result + "\n");
        }

        System.out.println(sb);
    }
}
