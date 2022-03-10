package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main4948 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/4948.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[246913];
        for (int i = 2; i < 246913; i++) {
            arr[i] = i;
        }
        for (int i = 2; i < 246913; i++) {
            if (arr[i] != 0) {
                for (int j = 2 * i; j < 246913; j += i) {
                    if (arr[j] != 0 && j % i == 0) {
                        arr[j] = 0;
                    }
                }
            }
        }
        while (N != 0) {
            int count = 0;
            for (int i = N + 1; i <= 2 * N; i++) {
                if (arr[i] != 0) count++;
            }
            if (N == 1) count = 1;
            System.out.println(count);
            N = Integer.parseInt(br.readLine());
        }
    }
}
