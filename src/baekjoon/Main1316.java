package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1316 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1316.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            boolean[] cArr = new boolean[26];
            boolean flag = true;
            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);
                if (!cArr[c - 'a']) {
                    int k = 0;
                    cArr[c - 'a'] = true;
                    while (j + k + 1 < s.length() && s.charAt(j + k + 1) == c) {
                        k++;
                    }
                    j += k;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) count++;
        }
        System.out.println(count);
    }
}
