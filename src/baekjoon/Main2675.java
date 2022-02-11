package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2675 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2675.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String s = st.nextToken();
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < K; j++) {
                    System.out.print(s.charAt(i));
                }
            }
            System.out.println();
        }

    }
}
