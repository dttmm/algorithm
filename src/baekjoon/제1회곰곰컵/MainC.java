package baekjoon.제1회곰곰컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainC {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/제1회곰곰컵/C.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int c = 0;
        int not_c = 0;
        for (int i = 0; i < N; i++) {
            char word = s.charAt(i);
            if (word == 'C') c++;
            else not_c++;
        }
        int answer = 0;
        if (c % (not_c + 1) == 0) answer = c / (not_c + 1);
        else answer = c / (not_c + 1) + 1;

        System.out.println(answer);
    }
}
