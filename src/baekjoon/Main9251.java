package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 부분집합을 이용하면 풀 수 있지만
 경우의 수가 말이 안된다? -> 99.99% dp문제라 확신
 하지만 dp 아이디어가 떠오르지 않았음

 뭔가 해시를 이용해서 풀 수 있을 것만 같아서 해시로 도전함
 첫 번째 문자열에서 각 알파벳의 인덱스 정보를 담아두고
 두 번째 문자열에서 해당 인덱스 정보를 이용하여 풀려고 했지만 실패

 결국 답을 찾지 못하고 LCS 공부함
 역쉬 dp는 알 수 없는 세상이다
 */
public class Main9251 {

    static String s1;
    static String s2;
    static int[][] d;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9251.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s1 = br.readLine();
        s2 = br.readLine();
        d = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= s2.length(); j++) {
                char c2 = s2.charAt(j - 1);

                if (c1 == c2) d[i][j] = d[i - 1][j - 1] + 1;
                else d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
            }
        }

        System.out.println(d[s1.length()][s2.length()]);
    }
}
