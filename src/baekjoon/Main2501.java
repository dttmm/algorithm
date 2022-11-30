package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2501 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2501.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int x = 0;
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                x++;
                if (x == K) {
                    answer = i;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
