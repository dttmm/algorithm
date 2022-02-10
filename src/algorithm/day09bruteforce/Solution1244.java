package algorithm.day09bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 58분
public class Solution1244 {
    static int[] arr;
    static int max;
    static int size;

    public static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void cal(int n, int r) {
        if (r == 0) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i] * Math.pow(10, n - 1 - i);
            }
            max = Math.max(sum, max);
            return;
        }

        for (int i = n - 2; i >= 0; i--) {
            swap(i, n - 1);
            cal(size, r - 1);
            swap(i, n - 1);
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day09_1244.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            arr = new int[s.length()];
            size = arr.length;
            max = Integer.MIN_VALUE;
            for (int i = 0; i < s.length(); i++) {
                arr[i] = s.charAt(i) - '0';
            }
            int r = Integer.parseInt(st.nextToken());

            for (int k = arr.length; k > 0; k--) {
                cal(k, r);
            }
            System.out.println("#" + test_case + " " + max);

        }
    }
}