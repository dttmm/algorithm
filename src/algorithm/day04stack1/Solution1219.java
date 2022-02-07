package algorithm.day04stack1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1219 {

    public static int next(int[] arr1, int[] arr2, int cur) {
        int flag = 0;
        if (arr1[cur] == 99 || arr2[cur] == 99) {
            return 1;
        }
        if (arr1[cur] != 0) {
            flag = next(arr1, arr2, arr1[cur]);
            if (flag == 1){
                return flag;
            }
        }
        if (arr2[cur] != 0) {
            flag = next(arr1, arr2, arr2[cur]);
            if (flag == 1){
                return flag;
            }
        }
        return flag;
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day04_1219.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int case_num = sc.nextInt();
            int N = sc.nextInt();
            int[] arr1 = new int[100];
            int[] arr2 = new int[100];

            for (int i = 0; i < N; i++) {
                int n1 = sc.nextInt();
                int n2 = sc.nextInt();
                if (arr1[n1] == 0) {
                    arr1[n1] = n2;
                } else {
                    arr2[n1] = n2;
                }
            }

            int result = next(arr1, arr2, 0);

            System.out.println("#" + case_num + " " + result);

        }
    }
}