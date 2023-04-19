package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 세그먼트트리 공부하고 풀음
 인터넷에 나와 있는 복잡한 세그먼트 트리말고
 직접 세그먼트 트리 간단하게 구현하여 풀음
 */
public class Main2042 {

    static int N;
    static int M;
    static int K;
    static long[] arr;
    static long[] tree;

    // 트리 초기화
    static void initTree() {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)); // 트리 높이
        int treeCount = (int) Math.pow(2, h + 1);
        tree = new long[treeCount];

        int startIndex = treeCount / 2;
        // 리프 노드 초기화
        for (int i = 0; i < N; i++) {
            tree[startIndex + i] = arr[i];
        }

        // 구간 합 구하기
        for (int i = tree.length - 1; i > 1; i--) {
            int parentIndex = i / 2;
            tree[parentIndex] += tree[i];
        }
    }

    // 트리의 구간합 구하기
    static long sum(int start, int end, long total) {
        if (start > end) return total;

        if (start % 2 == 1) total += tree[start];
        if (end % 2 == 0) total += tree[end];

        start = (start + 1) / 2;
        end = (end - 1) / 2;

        return sum(start, end, total);
    }

    // 트리의 값 변경하기
    static void update(int index, long diff) {
        if (index < 1) return;

        tree[index] += diff;
        update(index / 2, diff);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2042.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        initTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            int n1 = Integer.parseInt(st.nextToken());
            long n2 = Long.parseLong(st.nextToken());

            // 업데이트
            if (type.equals("1")) {
                // 구간 -> 배열 인덱스 -> 트리 인덱스로 변환
                int index = n1 - 1 + tree.length / 2;
                long diff = n2 - tree[index];

                update(index, diff);
            }
            // 구간합 구하기
            else {
                // 구간 -> 배열 인덱스 -> 트리 인덱스로 변환
                int startIndex = n1 - 1 + tree.length / 2;
                int endIndex = (int) (n2 - 1 + tree.length / 2);
                long result = sum(startIndex, endIndex, 0);

                sb.append(result + "\n");
            }
        }

        System.out.println(sb);
    }
}
