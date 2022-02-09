package algorithm.day08start;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution1242 {

    public static int readTo10(String x){
        return Integer.parseInt(x,16);
    }


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day08_1242.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        T = 1;
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 세로
            int M = Integer.parseInt(st.nextToken()); //가로

            int[][] arr = new int[N][M];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String[] s = st.nextToken().split("");
                for (int j = M-1; j >=0; j--) {

                }
            }

        }
    }
}