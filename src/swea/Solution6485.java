package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution6485 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/6485.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int[] count = new int[5001];
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                for (int j = start; j <= end; j++) {
                    count[j]++;
                }
            }

            String s = "#" + test_case + " ";
            int P = Integer.parseInt(br.readLine());
            for (int i = 0; i < P; i++) {
                int n = Integer.parseInt(br.readLine());
                s += count[n] + " ";
            }
            System.out.println(s);
        }
    }
}