package baekjoon.대회2023.solvedac_Grand_Arena_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainC {

    static int N;
    static long[] arr;
    static long[] tree;
    static int treeSize;
    static int treeStartIndex;
    static int[] count;

    static void initTree() {
        int h = (int) Math.ceil((Math.log(N) / Math.log(2)));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
        count = new int[treeSize];
        treeStartIndex = treeSize / 2;

        for (int i = 0; i < N; i++) {
            tree[i + treeStartIndex] = arr[i];
        }

        for (int i = treeSize - 1; i > 0; i--) {
            int parent = i / 2;
            tree[parent] += tree[i];
        }
    }

    static void solve() {

        for (int i = 1; i < treeSize; i++) {
            long n = tree[i];
            if (n > 0) count[i]++;
            else if (n < 0) count[i]--;
        }

        for (int i = treeStartIndex - 1; i > 0; i--) {
            int leftIndex = i * 2;
            int rightIndex = leftIndex + 1;

            count[i] = Math.max(count[i], count[leftIndex] + count[rightIndex]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_1/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            arr = new long[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                arr[i] = n;
            }

            initTree();

            solve();

            if (count[1] > 0) sb.append("YES\n");
            else sb.append("NO\n");
        }

        System.out.println(sb);
    }
}
