package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 설계 2분 구현 4분
 해시
 볼링공(key)이 들어간 사물함(value)을 해시에 저장해줌
 */
public class Main28446 {

    static int N;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/28446.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new HashMap();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int query = Integer.parseInt(st.nextToken());


            if (query == 1) {
                int x = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                map.put(w, x);
            } else {
                int w = Integer.parseInt(st.nextToken());
                sb.append(map.get(w) + "\n");
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
