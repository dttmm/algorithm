package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2116 {

    public static int findSame(int[] a, int n) {
        for (int i = 0; i < 6; i++) {
            if (a[i] == n) return i;
        }
        return -1;
    }

    public static int findMax(int a, int b) {
        int max = 0;
        for (int i = 1; i <= 6; i++) {
            if (i != a && i != b && i > max) max = i;
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2116.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][6];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 4; j < 6; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        int max_sum = 0;
        for (int i = 0; i < 6; i++) {
            int bottom = arr[0][i];
            int top = arr[0][(i + 3) % 6];
            int max = findMax(bottom, top);
            int sum = 0;
            sum += max;
            for (int j = 1; j < N; j++) {
                int bottom_idx = findSame(arr[j], top);
                bottom = arr[j][bottom_idx];
                top = arr[j][(bottom_idx + 3) % 6];
                max = findMax(bottom, top);
                sum += max;
            }
            if (sum > max_sum) max_sum = sum;
        }
        System.out.println(max_sum);
    }
}
