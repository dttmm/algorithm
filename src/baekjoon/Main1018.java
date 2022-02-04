package baekjoon;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main1018 {
    static char[][] arr;

    static public int rowCheck(char c, int i, int j) {
        int count = 0;
        for (int k = 0; k < 8; k++) {

            if (k % 2 == 0) {
                if (arr[i + k][j] != c) {
                    count++;
                }
                if (arr[i + k][2 + j] != c) {
                    count++;
                }
                if (arr[i + k][4 + j] != c) {
                    count++;
                }
                if (arr[i + k][6 + j] != c) {
                    count++;
                }
                if (arr[i + k][1 + j] == c) {
                    count++;
                }
                if (arr[i + k][3 + j] == c) {
                    count++;
                }
                if (arr[i + k][5 + j] == c) {
                    count++;
                }
                if (arr[i + k][7 + j] == c) {
                    count++;
                }
            } else {
                if (arr[i + k][j] == c) {
                    count++;
                }
                if (arr[i + k][2 + j] == c) {
                    count++;
                }
                if (arr[i + k][4 + j] == c) {
                    count++;
                }
                if (arr[i + k][6 + j] == c) {
                    count++;
                }
                if (arr[i + k][1 + j] != c) {
                    count++;
                }
                if (arr[i + k][3 + j] != c) {
                    count++;
                }
                if (arr[i + k][5 + j] != c) {
                    count++;
                }
                if (arr[i + k][7 + j] != c) {
                    count++;
                }
            }

        }
        return count;
    }

    static public int rowCheckReverse(char c, int i, int j) {
        int count = 0;
        for (int k = 0; k < 8; k++) {

            if (k % 2 == 0) {
                if (arr[i + k][j] == c) {
                    count++;
                }
                if (arr[i + k][2 + j] == c) {
                    count++;
                }
                if (arr[i + k][4 + j] == c) {
                    count++;
                }
                if (arr[i + k][6 + j] == c) {
                    count++;
                }
                if (arr[i + k][1 + j] != c) {
                    count++;
                }
                if (arr[i + k][3 + j] != c) {
                    count++;
                }
                if (arr[i + k][5 + j] != c) {
                    count++;
                }
                if (arr[i + k][7 + j] != c) {
                    count++;
                }
            } else {
                if (arr[i + k][j] != c) {
                    count++;
                }
                if (arr[i + k][2 + j] != c) {
                    count++;
                }
                if (arr[i + k][4 + j] != c) {
                    count++;
                }
                if (arr[i + k][6 + j] != c) {
                    count++;
                }
                if (arr[i + k][1 + j] == c) {
                    count++;
                }
                if (arr[i + k][3 + j] == c) {
                    count++;
                }
                if (arr[i + k][5 + j] == c) {
                    count++;
                }
                if (arr[i + k][7 + j] == c) {
                    count++;
                }
            }

        }
        return count;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1018.txt"));

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        arr = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = sc.next();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
            }
        }


        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                char c = arr[i][j];
                int count = rowCheck(c, i, j);
                min = count < min ? count : min;

                count = rowCheckReverse(c, i, j);
                min = count < min ? count : min;
            }
        }
        System.out.println(min);
    }
}
