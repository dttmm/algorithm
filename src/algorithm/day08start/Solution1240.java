package algorithm.day08start;

import java.io.FileInputStream;
import java.util.Scanner;

// 1시간 5분
public class Solution1240 {
    public static String[] code = {"3211", "2221", "2122", "1411", "1132", "1231", "1114", "1312", "1213", "3112"};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day08_1240.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 세로
            int M = sc.nextInt(); // 가로
            int[][] arr = new int[N][M];

            for (int i = 0; i < N; i++) {
                String[] s = sc.next().split("");
                for (int j = 0; j < M; j++) {
                    arr[i][j] = Integer.parseInt(s[j]);
                }
            }

            boolean flag = false;
            int start_i = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == 1) {
                        flag = true;
                        start_i = i;
                        break;
                    }
                }
                if (flag) break;
            }

            flag = false;
            int start_j = 0;
            for (int j = 0; j < M - 7; j++) {
                String s = "";
                int pre = arr[start_i][j];
                int count = 1;
                for (int k = 1; k < 7; k++) {
                    if (pre == arr[start_i][j + k]) {
                        count++;
                    } else {
                        s += count;
                        count = 1;
                    }
                    pre = arr[start_i][j + k];
                }
                s += count;
                for (int i = 0; i < 10; i++) {
                    if (s.equals(code[i])) {
                        flag = true;
                        start_j = j;
                        break;
                    }
                }
                if (flag) {
                    flag = false;
                    s = "";
                    pre = arr[start_i][start_j + 7];
                    count = 1;
                    for (int k = 1; k < 7; k++) {
                        if (pre == arr[start_i][j + k]) {
                            count++;
                        } else {
                            s += count;
                            count = 1;
                        }
                        pre = arr[start_i][j + k];
                    }
                    s += count;
                    for (int i = 0; i < 10; i++) {
                        if (s.equals(code[i])) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
            }

            int[] found = new int[8];
            for (int x = 0; x < 8; x++, start_j += 7) {
                String s = "";
                int pre = arr[start_i][start_j];
                int count = 1;
                for (int k = 1; k < 7; k++) {
                    if (pre == arr[start_i][start_j + k]) {
                        count++;
                    } else {
                        s += count;
                        count = 1;
                    }
                    pre = arr[start_i][start_j + k];
                }
                s += count;
                for (int i = 0; i < 10; i++) {
                    if (s.equals(code[i])) {
                        found[x] = i;
                    }
                }
            }

            int checkCode = 0;
            for (int i = 0; i < 8; i += 2) {
                checkCode += found[i];
            }
            checkCode *= 3;
            for (int i = 1; i < 8; i += 2) {
                checkCode += found[i];
            }

            System.out.print("#" + test_case + " ");
            if (checkCode % 10 == 0) {
                int sum = 0;
                for (int i = 0; i < 8; i++) {
                    sum += found[i];
                }
                System.out.println(sum);
            } else {
                System.out.println(0);
            }
        }
    }
}