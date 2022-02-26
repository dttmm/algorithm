package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution1289 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/1289.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String s = br.readLine();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                int n = s.charAt(i) - '0';
                if (n == 1) {
                    count++;
                    if (i + 1 >= s.length()) break;
                    n = s.charAt(++i) - '0';
                    int flag = 1;
                    while (i < s.length()) {
                        if ((flag == 1 && n == 0) || (flag == 0 && n == 1)) {
                            count++;
                            flag = n;
                        }
                        if (i + 1 >= s.length()) break;
                        n = s.charAt(++i) - '0';
                    }
                }
            }
            System.out.println("#" + test_case + " " + count);
        }
    }
}