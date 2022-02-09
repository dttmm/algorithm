package algorithm.day07tree;

import java.io.FileInputStream;
import java.util.Scanner;

// 27분 입력받는 부분에서 13분 날림
public class Solution1231 {
    static char[] arr;
    static int N;

    public static void inorder(int v) {
        if (2 * v <= N && arr[2 * v] != '\0') {
            inorder(2 * v);
        }
        System.out.print(arr[v]);
        if (2 * v + 1 <= N && arr[2 * v + 1] != '\0') {
            inorder(2 * v + 1);
        }

    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day07_1231.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(sc.nextLine());
            arr = new char[N + 1];
            for (int i = 1; i <= N; i++) {
                String[] s = sc.nextLine().split(" ");
                arr[i] = s[1].charAt(0);
            }
            System.out.print("#" + test_case + " ");
            inorder(1);
            System.out.println();
        }
    }
}