package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main1269 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1269.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Map<Integer, Boolean> map = new HashMap();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            int n = Integer.parseInt(st.nextToken());
            map.put(n, true);
        }

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (map.containsKey(n)) sum++;
        }

        System.out.println(A + B - sum * 2);
    }
}
