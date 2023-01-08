package baekjoon.대회2023.제1회_보라매컵_예선_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_보라매컵_예선_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        for (int i = 0; i < 3; i++) {
            int n = Integer.parseInt(st.nextToken());
            answer += Math.min(N, n);
        }
        System.out.println(answer);
    }
}
