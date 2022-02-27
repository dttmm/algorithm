package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2304 {
    static int[] arr;


    public static int findMaxLeft(int s, int e) {
        int max_idx = e;
        for (int i = e - 1; i >= s; i--) {
            if (arr[i] > arr[max_idx]) {
                max_idx = i;
            }
        }
        return max_idx;
    }

    public static int findMaxRight(int s, int e) {
        int max_idx = s;
        for (int i = s + 1; i <= e; i++) {
            if (arr[i] > arr[max_idx]) {
                max_idx = i;
            }
        }
        return max_idx;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2304.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new int[1002];

        int max_idx = 0;
        int bound = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr[L] = H;
            if (H > arr[max_idx]) {
                max_idx = L;
            }
            if (L > bound) {
                bound = L;
            }
        }

        for (int i = max_idx; i > 0;) {
            int next_max_idx = findMaxLeft(1, i - 1);
            int h = arr[next_max_idx];
            if (h == 0) break;
            i--;
            while (i > next_max_idx) {
                arr[i--] = h;
            }
        }

        for (int i = max_idx; i <= bound;) {
            int next_max_idx = findMaxRight(i + 1, bound);
            int h = arr[next_max_idx];
            if (h == 0) break;
            i++;
            while (i < next_max_idx) {
                arr[i++] = h;
            }
        }

        int count = 0;
        for (int i = 1; i <= bound; i++) {
            count += arr[i];
        }

        System.out.println(count);
    }
}
