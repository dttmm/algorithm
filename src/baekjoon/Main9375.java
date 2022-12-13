package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 처음에는 비트마스킹으로 모든 옷 조합을 계산했는데
 메모리 초과 나옴
 도저히 이 방법 말고는 해결할 방법이 안보여서
 풀이 봤는데
 이게 웬걸...
 간단 수학문제였네.......
 */
public class Main9375 {

    static int N;
    static Map<String, Integer> map;
    static long total;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9375.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            map = new HashMap();
            total = 1;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String value = st.nextToken();
                String key = st.nextToken();
                if (map.containsKey(key)) map.put(key, map.get(key) + 1);
                else map.put(key, 1);
            }

            for (String key : map.keySet()) {
                int value = map.get(key);
                total *= (value + 1);
            }

            bw.write(total - 1 + "\n");
        }
        bw.flush();
        bw.close();
    }
}
