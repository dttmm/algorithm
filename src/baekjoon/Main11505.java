package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 설계 7분 구현 26분
 세그먼트트리 이용

 update함수를 짤 때
 노드에 기존 값을 나누고
 바뀐 값을 새로 곱해주는 식으로 짰는데
 새로운 값이 0이면
 이후에는 0에서 값이 안되는 상황 발생
 결국 자신의 자식 노드 두개를 곱해서 자신을 갱신해주는 식으로 update짬

 트리 초기화 하는 부분에서 반복문 잘 못 돌려서 틀림
 1번 ~ 트리 끝 까지 트리 값을 초기화 해줘야 되는데
 리프 노드 부모까지만 값을 초기화해줬넹..
 */
public class Main11505 {

    static final int MOD = 1000000007;
    static int N;
    static int M;
    static int K;
    static int[] arr;   // 기존 배열
    static int[] tree;  // 트리
    static int treeSize;
    static int treeStartIndex;

    // 트리 초기화
    static void initTree() {
        // 높이 구해서 트리 초기화
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        treeStartIndex = treeSize / 2;
        tree = new int[treeSize];

        // 트리값 1로 초기화
        for (int i = 1; i < treeSize; i++) {
            tree[i] = 1;
        }

        // 기존 값 트리의 리프노드에 넣음
        for (int i = 0; i < N; i++) {
            tree[i + treeStartIndex] = arr[i];
        }

        // 부모 노드값 초기화
        for (int i = treeSize - 1; i > 0; i--) {
            int parentIndex = i / 2;
            tree[parentIndex] = (int) (((long) tree[parentIndex] * tree[i]) % MOD);
        }
    }

    // 쿼리
    static int query(int start, int end, int total) {
        if (start > end) return total;

        if (start % 2 == 1) total = (int) (((long) total * tree[start]) % MOD);
        if (end % 2 == 0) total = (int) (((long) total * tree[end]) % MOD);

        start = (start + 1) / 2;
        end = (end - 1) / 2;

        return query(start, end, total);
    }

    // 업데이트
    static void update(int index) {
        if (index == 0) return;

        // 자식들의 값을 이용해서 자신의 값 업데이트
        int nextNum = (int) (((long) tree[index * 2] * tree[index * 2 + 1]) % MOD);

        tree[index] = nextNum;

        update(index / 2);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11505.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[i] = n;
        }

        // 트리 초기화
        initTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            // 업데이트 수행
            if (type == 1) {
                int targetIndex = Integer.parseInt(st.nextToken()) - 1;
                int treeIndex = targetIndex + treeStartIndex;
                int nextNum = Integer.parseInt(st.nextToken());

                // 자신 값 업데이트하고
                tree[treeIndex] = nextNum;
                // 부모 값 업데이트
                update(treeIndex / 2);
            }
            // 쿼리 수행
            else {
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken()) - 1;
                int startIndex = start + treeStartIndex;
                int endIndex = end + treeStartIndex;

                int result = query(startIndex, endIndex, 1);
                sb.append(result + "\n");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
