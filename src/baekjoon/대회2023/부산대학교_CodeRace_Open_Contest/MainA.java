package baekjoon.대회2023.부산대학교_CodeRace_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 탐색 문제
 앞으로 탐색해가면서
 이전 값이 더 작으면 카운트 해줌
 */
public class MainA {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/부산대학교_CodeRace_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int pre = 0;
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            if (pre <= cur) answer++;
            pre = cur;
        }

        System.out.println(answer);
    }
}
