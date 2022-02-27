package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution1860 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1860.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            String result = "Possible";
            for (int i = 0; i < N; i++) {
                int time = arr[i];
                int made = time / M * K;
                if (made < i+1) {
                    result = "Impossible";
                    break;
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
    }
}