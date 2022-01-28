package day01array1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1208 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day01_1208.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            System.out.print("#" + test_case + " ");
            int dump = sc.nextInt();
            int N = 100;
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            int[] count = new int[N];
            for (int i = 0; i < N; i++) {
                count[arr[i] - 1]++;
            }

            for (int i = 0; i < dump; i++) {
                int min = 0;
                int max = 100;
                for (int j = 0; j < N; j++) {
                    if (count[j] != 0) {
                        min = j;
                        break;
                    }
                }
                for (int j = N - 1; j >= 0; j--) {
                    if (count[j] != 0) {
                        max = j;
                        break;
                    }
                }

                count[max]--;
                count[max - 1]++;
                count[min]--;
                count[min + 1]++;
            }

            int min = 100;
            int max = 0;
            for (int i = 0; i < N; i++) {
                if (count[i] != 0) {
                    min = i;
                    break;
                }
            }

            for (int i = N - 1; i >= 0; i--) {
                if (count[i] != 0) {
                    max = i;
                    break;
                }
            }

            System.out.println(max - min);
        }
    }
}
