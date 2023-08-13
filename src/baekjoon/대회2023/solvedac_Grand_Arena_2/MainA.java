package baekjoon.대회2023.solvedac_Grand_Arena_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 구현 2분
 간단한 연산 문제
 */
public class MainA {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/solvedac_Grand_Arena_2/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int total1 = 0;     // 1부터 N까지 합
        int total3 = 0;     // 1부터 N까지 수의 세제곱 합
        for (int i = 1; i <= N; i++) {
            total1 += i;
            total3 += Math.pow(i, 3);
        }
        int total2 = (int) Math.pow(total1, 2); // 1부터 N까지 수의 합 제곱

        StringBuilder sb = new StringBuilder();
        sb.append(total1 + "\n");
        sb.append(total2 + "\n");
        sb.append(total3 + "\n");
        System.out.println(sb);
    }
}
