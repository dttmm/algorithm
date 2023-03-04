package baekjoon.대회2023.성균관대학교_프로그래밍_경진대회_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 수학 문제
 */
public class MainA {

    static int N;
    static int M;
    static int K;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/성균관대학교_프로그래밍_경진대회_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (K < N + M - 1) {
            System.out.println("NO");
            return;
        }

        StringBuilder sb = new StringBuilder("YES\n");
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(i + j + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
