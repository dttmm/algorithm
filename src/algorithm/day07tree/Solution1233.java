package algorithm.day07tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// 20분
// 알고리즘보다 입력받는게 더 힘든 듯
public class Solution1233 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/algorithm/input_day07_1233.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int flag = 1;

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                String operator = st.nextToken();
                if (!(operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/"))) {
                    if (st.hasMoreTokens()) {
                        flag = 0;
                    }
                }
            }

            System.out.println("#" + test_case + " " + flag);
        }
    }
}