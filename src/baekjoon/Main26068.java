package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 설계 0분 구현 3분
 간단한 분기 문제
 문자열 쪼개는거 기억 못할뻔
 */
public class Main26068 {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/26068.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("-");
            if (Integer.parseInt(s[1]) > 90) continue;

            answer++;
        }

        System.out.println(answer);
    }
}
