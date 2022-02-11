package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11720 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11720.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int sum = 0;
        String s = br.readLine();
        for (int i = 0; i < N; i++) {
            sum += s.charAt(i) - '0';
        }
        System.out.println(sum);
    }
}
