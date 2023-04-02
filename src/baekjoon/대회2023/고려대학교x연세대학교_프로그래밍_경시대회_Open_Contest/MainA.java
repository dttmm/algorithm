package baekjoon.대회2023.고려대학교x연세대학교_프로그래밍_경시대회_Open_Contest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 LCS를 변형하여 문제를 풀음
 입력으로 주어준 문자열을 하나씩 돌면서
 KOREA와 YONSEI중 일치하는 글자가 있는지 검사하고
 이전 글자까지 모두 포함하는 경우 +1 해줌

 예를 들어 R이 나온 경우
 KOREA에서는 앞에 KO까지 모두 나온 적이 있는지 검사를 한번 더 하여
 +1 해줌
 */
public class MainA {

    final static String K = "KOREA";
    final static String Y = "YONSEI";
    static String S;
    static int N;
    static int[][] d_k;
    static int[][] d_y;

    static String solve() {
        for (int i = 1; i <= N; i++) {
            char c = S.charAt(i - 1);

            // "KOREA" 검사
            for (int j = 1; j <= K.length(); j++) {
                char c_k = K.charAt(j - 1);

                // 글자가 일치하지 않거나 || 이전 글자가 모두 포함되지 않은 경우 -> 패쓰
                if (c_k != c || d_k[i - 1][j - 1] != j - 1) {
                    d_k[i][j] = Math.max(d_k[i - 1][j], d_k[i][j - 1]);
                    continue;
                }

                d_k[i][j] = d_k[i - 1][j - 1] + 1;
            }

            // "YONSEI" 검사
            for (int j = 1; j <= Y.length(); j++) {
                char c_y = Y.charAt(j - 1);

                // 글자가 일치하지 않거나 || 이전 글자가 모두 포함되지 않은 경우 -> 패쓰
                if (c_y != c || d_y[i - 1][j - 1] != j - 1) {
                    d_y[i][j] = Math.max(d_y[i - 1][j], d_y[i][j - 1]);
                    continue;
                }

                d_y[i][j] = d_y[i - 1][j - 1] + 1;
            }

            // 지금까지 결과 중에 완성된 글자가 있는지 확인
            if (d_k[i][K.length()] == K.length()) return K;
            if (d_y[i][Y.length()] == Y.length()) return Y;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/고려대학교x연세대학교_프로그래밍_경시대회_Open_Contest/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        N = S.length();
        d_k = new int[N + 1][K.length() + 1];
        d_y = new int[N + 1][Y.length() + 1];

        System.out.println(solve());
    }
}
