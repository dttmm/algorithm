package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution6190 {
    static int N;
    static int[] arr;
    static int[] tr;
    static int max;

    public static void p(int k, int s) {
        if (k == 2) {
            boolean flag = true;
            int num = tr[0] * tr[1];
            int n = num % 10;
            int rest = num / 10;
            int pre = n;
            while (rest != 0 || n != 0) {
                pre = n;
                n = rest % 10;
                rest /= 10;
                if (n > pre) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                max = Math.max(num, max);
            }
        } else {
            for (int i = s; i < N; i++) {
                tr[k] = arr[i];
                p(k + 1, i + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/6190.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            tr = new int[2];
            max = -1;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            p(0, 0);
            System.out.println("#" + test_case + " " + max);
        }
    }
}