package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution1288 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1288.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());
            boolean[] visited = new boolean[10];
            int flag = 0;
            int count = 0;
            while (flag != 10) {
                count++;
                int cur = count * N;
                while (cur != 0) {
                    int one = cur % 10;
                    cur /= 10;
                    if (!visited[one]) {
                        visited[one] = true;
                        flag++;
                    }
                }
            }
            System.out.println("#" + test_case + " " + count * N);
        }
    }
}