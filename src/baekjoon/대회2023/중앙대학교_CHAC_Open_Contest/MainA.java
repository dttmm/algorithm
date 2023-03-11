package baekjoon.대회2023.중앙대학교_CHAC_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 간단한 카운트 문제
 무효 처리의 경우
 무효표가 총 투표수의 (N + 1) / 2이상인 경우 과반수임
 */
public class MainA {

    static int N;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/중앙대학교_CHAC_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int num = 0;    // 찬반 투표 카운트
        int invalid = 0;    // 기권표 카운트

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            num += n;

            if (n != 0) continue;
            invalid++;
        }

        String answer = "";

        // 무효 인 경우
        if (invalid >= (N + 1) / 2) {
            answer = "INVALID";
        } else {
            // 통과인 경우
            if (num > 0) answer = "APPROVED";
            // 통과안된 경우
            else answer = "REJECTED";
        }

        System.out.println(answer);
    }
}
