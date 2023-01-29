package baekjoon.대회2023.제_2회_고려대학교_MatKor_Cup;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 단순 구현 문제
 */
public class MainA {

    static int T;
    static int S;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제_2회_고려대학교_MatKor_Cup/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        if (T >= 12 && T <= 16 && S == 0) System.out.println(320);
        else System.out.println(280);
    }
}
