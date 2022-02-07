package algorithm.day04stack1;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1217 {

    public static int pow(int N, int M) {
        if (M < 1) {
            return 1;
        }
        return N * pow(N, M - 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day04_1217.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            int case_num = sc.nextInt();
            int N = sc.nextInt();
            int M = sc.nextInt();

            int result = pow(N, M);

            System.out.println("#" + case_num + " "+result);
        }
    }
}
