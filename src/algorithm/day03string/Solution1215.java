package algorithm.day03string;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1215 {

    public static int cal(char[] c, int i, int len) {
        int sum = 0;
        for (int j = 0; j < c.length - len + 1; j++) {
            String s = "";
            for (int k = j; k < j + len; k++) {
                s += c[k];
            }
            if (isPalindrome(s)) {
                sum += 1;
            }
        }
        return sum;
    }

    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void swap(char[][] arr, int n1, int n2) {
        char temp = arr[n1][n2];
        arr[n1][n2] = arr[n2][n1];
        arr[n2][n1] = temp;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day03_1215.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {


            // 여기서부터 알고리즘 작성하세요.
            int len = sc.nextInt();
            String s = sc.next();
            int N = s.length();
            char[][] cArr = new char[N][N];

            for (int i = 0; i < N; i++) {
                cArr[0][i] = s.charAt(i);
            }

            for (int i = 1; i < N; i++) {
                s = sc.next();
                for (int j = 0; j < N; j++) {
                    cArr[i][j] = s.charAt(j);
                }
            }

            int sum = 0;

            // 열 검사
            for (int i = 0; i < N; i++) {
                sum += cal(cArr[i], i, len);
            }

            // 행 검사할 차례 전치행렬로
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j > i) {
                        swap(cArr, i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                sum += cal(cArr[i], i, len);
            }

            System.out.println("#" + test_case + " " + sum);
        }
    }
}
