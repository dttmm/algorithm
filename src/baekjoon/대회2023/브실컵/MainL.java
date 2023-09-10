package baekjoon.대회2023.브실컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 13분 구현 14분
 구현
 */
public class MainL {

    static int N;
    static int Q;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/대회2023/브실컵/L.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new HashMap();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (map.get(n) == null) map.put(n, 1);
            else map.put(n, map.get(n) + 1);
        }

        Q = Integer.parseInt(br.readLine());
        for (int k = 0; k < Q; k++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            Map<Integer, Integer> mapA = new HashMap();
            for (int i = 0; i < n; i++) {
                int color = Integer.parseInt(st.nextToken());
                if (mapA.get(color) == null) mapA.put(color, 1);
                else mapA.put(color, mapA.get(color) + 1);
            }

            boolean flag = true;
            for (int key : mapA.keySet()) {
                if (map.get(key) == null) {
                    flag = false;
                    break;
                }

                if (mapA.get(key) > map.get(key)) {
                    flag = false;
                    break;
                }
            }

            st = new StringTokenizer(br.readLine());
            if (!flag) continue;

            for (int key : mapA.keySet()) {
                map.put(key, map.get(key) - mapA.get(key));
            }

            n = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                int color = Integer.parseInt(st.nextToken());
                if (map.get(color) == null) map.put(color, 1);
                else map.put(color, map.get(color) + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        int total = 0;
        for (int key : map.keySet()) {
            total += map.get(key);
        }
        sb.append(total + "\n");

        if (total != 0) {
            for (int key : map.keySet()) {
                int n = map.get(key);
                for (int i = 0; i < n; i++) {
                    sb.append(key + " ");
                }
            }
        }

        System.out.println(sb);
    }
}
