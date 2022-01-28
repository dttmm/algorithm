package day01array1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1204 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day01_1204.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {

            int case_num = sc.nextInt();
            System.out.print("#" + case_num + " ");
            sc.nextLine();
            String[] s = sc.nextLine().split(" ");

            int N = 1000;
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(s[i]);
            }

            int[] count = new int[101];

            for (int i = 0; i < N; i++) {
                count[arr[i]]++;
            }

            int max = 0;
            int max_idx = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > max) {
                    max = count[i];
                    max_idx = i;
                } else if ((count[i] == max) && (i > max_idx)) {
                    max_idx = i;
                }
            }
            System.out.println(max_idx);
        }
    }
}
