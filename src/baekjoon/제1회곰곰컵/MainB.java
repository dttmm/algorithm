package baekjoon.제1회곰곰컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MainB {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/제1회곰곰컵/B.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String, Boolean> map = new HashMap();
        int total = 0;

        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if (s.equals("ENTER")) {
                map.clear();
            } else {
                if (map.get(s) == null) {
                    total++;
                    map.put(s, true);
                }
            }
        }

        System.out.println(total);
    }
}
