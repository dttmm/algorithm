package algorithm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ant {

    public static boolean isInwall(int i, int j, int N) {
        if ((i >= 0 && i < N) && (j >= 0 && j < N)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_ant.txt"));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = sc.nextInt();
            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            char pos = 'r';

            int i = 0;
            int j = 0;
            int count = 0;
            while (true) {
                if (pos == 'r') {
                    j++;
                    if (!isInwall(i, j, N)) break;
                    if (arr[i][j] == 1) {
                        pos = 't';
                    } else if (arr[i][j] == 2) {
                        pos = 'd';
                    }
                } else if (pos == 'l') {
                    j--;
                    if (!isInwall(i, j, N)) break;
                    if (arr[i][j] == 1) {
                        pos = 'd';
                    } else if (arr[i][j] == 2) {
                        pos = 't';
                    }
                } else if (pos == 't') {
                    i--;
                    if (!isInwall(i, j, N)) break;
                    if (arr[i][j] == 1) {
                        pos = 'r';
                    } else if (arr[i][j] == 2) {
                        pos = 'l';
                    }
                } else if (pos == 'd') {
                    i++;
                    if (!isInwall(i, j, N)) break;
                    if (arr[i][j] == 1) {
                        pos = 'l';
                    } else if (arr[i][j] == 2) {
                        pos = 'r';
                    }
                }
                count++;
            }
            System.out.println("#" + test_case + " " + count);
        }
    }
}