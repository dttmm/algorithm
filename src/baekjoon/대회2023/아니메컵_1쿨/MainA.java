package baekjoon.대회2023.아니메컵_1쿨;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/아니메컵_1쿨/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int answer = 0;

        answer += s.length();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ':') answer++;
            else if (c == '_') answer += 5;
        }

        System.out.println(answer);
    }
}
