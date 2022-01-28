package day01array1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1206 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day01_1206.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            System.out.print("#" + test_case + " ");

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                int min = 255;
                if (2 <= i && i <= N - 3) {
                    for (int j = i - 2; j <= i + 2; j++) {
                        if (j != i) {
                            int diff = arr[i] - arr[j];
                            if ((diff > 0)) {
                                if (diff < min) {
                                    min = diff;
                                }
                            } else {
                                min = 0;
                                break;
                            }
                        }
                    }
                    count += min;
                }
            }

            System.out.println(count);
        }
    }
}
