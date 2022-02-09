package algorithm.day07tree;

import java.io.FileInputStream;
import java.util.Scanner;

// 41분
public class Solution1232 {
    public static String[][] arr;
    static int N;


    public static int inOrder(int node) {
        if (arr[0][node].equals("+") || arr[0][node].equals("-") || arr[0][node].equals("*") || arr[0][node].equals("/")) {
            int n1 = 0;
            if (arr[1][node] != null) {
                n1 = inOrder(Integer.parseInt(arr[1][node]));
            }
            String c = arr[0][node];  //연산자
            int n2 = 0;
            if (arr[2][node] != null) {
                n2 = inOrder(Integer.parseInt(arr[2][node]));
            }
            if (c.equals("+")) {
                return n1 + n2;
            } else if (c.equals("-")) {
                return n1 - n2;
            } else if (c.equals("*")) {
                return n1 * n2;
            } else {
                return n1 / n2;
            }
        } else {
            return Integer.parseInt(arr[0][node]);
        }
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day07_1232.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            N = Integer.parseInt(sc.nextLine());
            arr = new String[3][N + 1];
            for (int i = 0; i < N; i++) {
                String[] s = sc.nextLine().split(" ");
                int node = Integer.parseInt(s[0]);
                String c = s[1];
                arr[0][node] = c;
                if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                    arr[1][node] = s[2];
                    arr[2][node] = s[3];
                }
            }

            System.out.println("#" + test_case + " " + inOrder(1));

        }
    }
}