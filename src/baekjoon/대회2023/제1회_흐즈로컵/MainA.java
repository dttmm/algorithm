package baekjoon.대회2023.제1회_흐즈로컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 단순 수학 문제
 N, M이 모두 홀수인 경우에만 -1해줌
 */
public class MainA {

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/제1회_흐즈로컵/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N % 2 == 1 && M % 2 == 1) System.out.println(N * M - 1);
        else System.out.println(N * M);
    }
}
