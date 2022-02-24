package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11399 {
    static int N;
    static int[] arr;
    static int total;

    public static int findMin() {
        int min_idx = 0;
        for (int i = 1; i < N; i++) {
            if (arr[i] < arr[min_idx]) {
                min_idx = i;
            }
        }
        return min_idx;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11399.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        total = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int size = N;
        while (size > 0) {
            int min_idx = findMin();
            int sum = total + arr[min_idx];
            result += sum;
            total += arr[min_idx];
            arr[min_idx] = 1001;
            size--;
        }
        System.out.println(result);
    }
}
