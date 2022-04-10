package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main9012 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9012.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String answer = "YES";
            String s = br.readLine();
            int top = -1;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (c == '(') {
                    top++;
                } else if (c == ')' && top > -1) {
                    top--;
                } else {
                    answer = "NO";
                    break;
                }
            }
            if (top != -1) answer = "NO";
            System.out.println(answer);
        }
    }
}
