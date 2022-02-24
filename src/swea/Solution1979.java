package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1979 {
    static int[][] arr;
    static int sum;
    static int N;
    static int K;


    public static void find() {
        for (int i = 0; i < N; i++) {
            int k = 0;
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    k++;
                    j++;
                    while (j < N && arr[i][j] == 1) {
                        k++;
                        j++;
                    }
                    if (k == K) sum++;
                    k = 0;
                }
            }
        }
    }

    public static void swap(int[][] arr, int i1, int j1, int i2, int j2) {
        int temp = arr[i1][j1];
        arr[i1][j1] = arr[i2][j2];
        arr[i2][j2] = temp;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1979.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sum = 0;
            find();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    swap(arr, i, j, j, i);
                }
            }
            find();
            System.out.println("#" + test_case + " " + sum);
        }
    }
}