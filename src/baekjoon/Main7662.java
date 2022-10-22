package baekjoon;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main7662 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/7662.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());
            TreeMap<Long, Integer> map = new TreeMap();

            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                long n = Long.parseLong(st.nextToken());

                if (command.equals("I")) {
                    if (map.containsKey(n)) map.put(n, map.get(n) + 1);
                    else map.put(n, 1);
                } else {
                    if (n == 1) {
                        if (!map.isEmpty()) {
                            Map.Entry e = map.lastEntry();
                            if ((int) e.getValue() == 1) map.remove(e.getKey());
                            else map.put((long) e.getKey(), (int) e.getValue() - 1);
                        }
                    } else {
                        if (!map.isEmpty()) {
                            Map.Entry e = map.firstEntry();
                            if ((int) e.getValue() == 1) map.remove(e.getKey());
                            else map.put((long) e.getKey(), (int) e.getValue() - 1);
                        }
                    }
                }
            }

            if (!map.isEmpty()) {
                Map.Entry last = map.lastEntry();
                Map.Entry first = map.firstEntry();
                bw.write(last.getKey() + " " + first.getKey() + "\n");
            } else {
                bw.write("EMPTY\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
