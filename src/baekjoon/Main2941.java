package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 33분+10분
public class Main2941 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2941.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i + 1 < s.length()) {
                if (c == 'c') {
                    if (s.charAt(i + 1) == '=' || s.charAt(i + 1) == '-') {
                        i++;
                    }
                } else if (c == 'd') {
                    if (s.charAt(i + 1) == '-') {
                        i++;
                    } else if (i + 2 < s.length()) {
                        if (s.charAt(i + 1) == 'z' && s.charAt(i + 2) == '=') {
                            i += 2;
                        }
                    }
                } else if (c == 'l') {
                    if (s.charAt(i + 1) == 'j') {
                        i++;
                    }
                } else if (c == 'n') {
                    if (s.charAt(i + 1) == 'j') {
                        i++;
                    }
                } else if (c == 's') {
                    if (s.charAt(i + 1) == '=') {
                        i++;
                    }
                } else if (c == 'z') {
                    if (s.charAt(i + 1) == '=') {
                        i++;
                    }
                }
            }
            count++;
        }
        System.out.println(count);
    }
}
