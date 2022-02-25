package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution2007 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/2007.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            String s = br.readLine();
            int N = 30;

            int count = 0;
            for (int i = 1; i <= 10; i++) {
                count = 0;
                boolean flag = false;
                String target = "";
                for (int j = 0; j < i; j++) {
                    target += s.charAt(j);
                }

                for (int j = target.length(); j + i < N; j += target.length()) {
                    String target2 = "";
                    for (int k = 0; k < i; k++) {
                        target2 += s.charAt(j + k);
                    }
                    if (target.equals(target2)) {
                        count = i;
                        flag = true;
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag) break;
            }
            System.out.println("#" + test_case + " " + count);
        }
    }
}