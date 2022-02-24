package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution7964 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/7964.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;
            for (int i = 0; i < N; i++) {
                int count = 1;
                while (i < N && arr[i] == 0) {
                    if (count == D) {
                        sum++;
                        count = 0;
                        arr[i] = 1;
                    }
                    i++;
                    count++;

                }
            }
            System.out.println("#" + test_case + " " + sum);
        }
    }
}