package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1859 {
    static int[] arr;
    static int N;
    static int start;
    static int size;

    public static int findMaxIdx() {
        int max_idx = start;

        for (int i = start + 1; i < N; i++) {
            if (arr[i] > arr[max_idx]) {
                max_idx = i;
            }
        }

        return max_idx;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1859.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            start = 0;
            size = N;
            long profit = 0;
            while (size > 0) {
                int max_idx = findMaxIdx();
                int max_price = arr[max_idx];
                for (int i = start; i < max_idx; i++) {
                    if (arr[i] != 0) {
                        profit += max_price - arr[i];
                        arr[i] = 0;
                        size--;
                    }
                }
                arr[max_idx] = 0;
                size--;
                start = max_idx;
            }

            System.out.println("#" + test_case + " " + profit);
        }
    }
}