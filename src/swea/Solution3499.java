package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3499 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/3499.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            String[] s = new String[N];

            for (int i = 0; i < N; i++) {
                s[i] = st.nextToken();
            }

            System.out.print("#" + test_case + " ");

            if (N % 2 == 1) {
                for (int i = 0; i < N / 2; i++) {
                    System.out.print(s[i] + " ");
                    System.out.print(s[N / 2 + i + 1] + " ");
                }
                System.out.print(s[N / 2]);
            } else {
                for (int i = 0; i < N / 2; i++) {
                    System.out.print(s[i] + " ");
                    System.out.print(s[N / 2 + i] + " ");
                }
            }
            System.out.println();
        }
    }
}