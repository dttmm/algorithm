package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1065 {
    static int[] arr;
    static int[] diff;

    public static boolean solve(int x) {
        boolean flag = true;
        int count = 2;

        int pre = x % 10;
        arr[3] = x % 10;
        x = x / 10;
        while (x / 10 != 0 || x % 10 != 0) {
            arr[count] = x % 10;
            diff[count] = x % 10 - pre;
            count--;
            pre = x % 10;
            x = x / 10;
        }

        pre = diff[2];
        for (int i = 1; i >= 0; i--) {
            if (diff[i] != -10) {
                if (diff[i] != pre) {
                    flag = false;
                    break;
                }
                pre = diff[i];
            } else break;
        }
        return flag;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1065.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        arr = new int[]{-1, -1, -1, -1};
        diff = new int[]{-10, -10, -10};
        for (int i = 1; i <= N; i++) {
            if (solve(i)) count++;
        }
        System.out.println(count);
    }
}
