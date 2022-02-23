package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1974 {
    static int[][] arr;
    static int flag;


    public static void check(int start_i, int start_j) {
        int[] count = new int[10];
        for (int i = start_i; i < start_i + 3; i++) {
            for (int j = start_j; j < start_j + 3; j++) {
                if (count[arr[i][j]] != 0) {
                    flag = 0;
                    break;
                }
                count[arr[i][j]]++;
            }
            if (flag == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1974.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = 9;
            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            flag = 1;

            for (int i = 0; i < N; i++) {
                int[] count = new int[10];
                for (int j = 0; j < N; j++) {
                    if (count[arr[i][j]] != 0) {
                        flag = 0;
                        break;
                    }
                    count[arr[i][j]]++;
                }
                if (flag == 0) {
                    break;
                }
            }

            if (flag == 1) {
                for (int j = 0; j < N; j++) {
                    int[] count = new int[10];
                    for (int i = 0; i < N; i++) {
                        if (count[arr[i][j]] != 0) {
                            flag = 0;
                            break;
                        }
                        count[arr[i][j]]++;
                    }
                    if (flag == 0) {
                        break;
                    }
                }
            }

            for (int i = 0; i < N; i += 3) {
                for (int j = 0; j < N; j += 3) {
                    check(i, j);
                    if (flag == 0) {
                        break;
                    }
                }
                if (flag == 0) {
                    break;
                }
            }

            System.out.println("#" + test_case + " " + flag);
        }
    }
}