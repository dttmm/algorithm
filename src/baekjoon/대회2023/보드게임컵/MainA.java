package baekjoon.대회2023.보드게임컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 계산 문제
 */
public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/보드게임컵/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int pre = -1;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (n - pre == 1) {
                pre = n;
                continue;
            }
            answer += n;
            pre = n;
        }
        System.out.println(answer);
    }
}
