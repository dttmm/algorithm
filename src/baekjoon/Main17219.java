package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main17219 {

    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/17219.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Map<String, String> map = new HashMap();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String key = st.nextToken();
            String value = st.nextToken();

            map.put(key, value);
        }

        for (int i = 0; i < M; i++) {
            bw.write(map.get(br.readLine()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
