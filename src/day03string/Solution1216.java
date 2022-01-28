package day03string;

import java.io.FileInputStream;
import java.util.Scanner;

public class Solution1216 {

    public static boolean isSame(char a, char b) {
        return a == b ? true : false;
    }

    public static void swap(char[][] cArr, int a, int b) {
        char temp = cArr[a][b];
        cArr[a][b] = cArr[b][a];
        cArr[b][a] = temp;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_day03_1216.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {


            // 여기서부터 알고리즘 작성하세요.
            int case_num = sc.nextInt();
            int N = 100;

            char[][] cArr = new char[100][100];
            for (int i = 0; i < N; i++) {
                String s = sc.next();
                for (int j = 0; j < N; j++) {
                    cArr[i][j] = s.charAt(j);
                }
            }

            int max = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - 1; j++) {
                    for (int k = N - 1; k > j; k--) {
                        int count = k - j + 1;
                        int limit = (k - j) / 2;
                        for (int x = 0; x <= limit; x++) {
                            int j_idx = j + x;
                            int k_idx = k - x;

                            if (!isSame(cArr[i][j_idx], cArr[i][k_idx])) {
                                count = 0;
                                break;
                            }
                        }
                        if (count>max){
                            max = count;
                        }

                    }
                }
            }

            //전치행렬
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (j > i) {
                        swap(cArr, i, j);
                    }
                }
            }


            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - 1; j++) {
                    for (int k = N - 1; k > j; k--) {
                        int count = k - j + 1;
                        int limit = (k - j) / 2;
                        for (int x = 0; x <= limit; x++) {
                            int j_idx = j + x;
                            int k_idx = k - x;

                            if (!isSame(cArr[i][j_idx], cArr[i][k_idx])) {
                                count = 0;
                                break;
                            }
                        }
                        if (count>max){
                            max = count;
                        }

                    }
                }
            }

            System.out.println("#" + case_num + " " + max);
        }
    }
}
