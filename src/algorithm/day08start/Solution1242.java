package algorithm.day08start;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution1242 {
    static int N;
    static int M;
    static int[][] arr;
    static String[] binary = {
            "0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"
    };
    static int[][][] code = new int[5][5][5];

    public static void sixteen(String[] s, int idx) {
        for (int i = 0; i < M; i++) {
            if (s[i].equals("A")) {
                s[i] = "10";
            } else if (s[i].equals("B")) {
                s[i] = "11";
            } else if (s[i].equals("C")) {
                s[i] = "12";
            } else if (s[i].equals("D")) {
                s[i] = "13";
            } else if (s[i].equals("E")) {
                s[i] = "14";
            } else if (s[i].equals("F")) {
                s[i] = "15";
            }
            int n = Integer.parseInt(s[i]);
            for (int j = 0; j < 4; j++) {
                arr[idx][4 * i + j] = binary[n].charAt(j) - '0';
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day08_1242.txt"));

        code[2][1][1] = 0;
        code[2][2][1] = 1;
        code[1][2][2] = 2;
        code[4][1][1] = 3;
        code[1][3][2] = 4;
        code[2][3][1] = 5;
        code[1][1][4] = 6;
        code[3][1][2] = 7;
        code[2][1][3] = 8;
        code[1][1][2] = 9;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 세로
            M = Integer.parseInt(st.nextToken()); //가로

            arr = new int[N][M * 4];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String[] s = st.nextToken().split("");
                sixteen(s, i);
            }

            int total = 0;
            for (int i = 1; i < N; i++) {
                int[] codes = new int[8];
                int idx = 7;
                for (int j = 4 * M - 1; j > 0; j--) {
                    if (arr[i][j] != 0 && arr[i - 1][j] == 0) {
                        int x = 1;
                        int y = 1;
                        int z = 1;
                        while (arr[i][--j] == 1) {
                            x++;
                        }
                        while (arr[i][--j] == 0) {
                            y++;
                        }
                        while (arr[i][--j] == 1) {
                            z++;
                        }

                        int min = Math.min(x, Math.min(y, z));
                        x /= min;
                        y /= min;
                        z /= min;

                        codes[idx--] = code[z][y][x];
                    }
                    if (idx == -1) {
                        int sum = 0;
                        for (int k = 0; k < 8; k++) {
                            if (k % 2 == 1) {
                                sum += codes[k];
                            } else {
                                sum += 3 * codes[k];
                            }
                        }
                        if (sum % 10 == 0) {
                            for (int k = 0; k < 8; k++) {
                                total += codes[k];
                            }
                        }
                        codes = new int[8];
                        idx = 7;
                    }
                }
            }
            System.out.println("#" + test_case + " " + total);
        }
    }
}