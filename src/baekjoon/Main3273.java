package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main3273 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/3273.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, Boolean> map = new HashMap();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            int target = x - n;
            if (map.get(target) != null) {
                answer++;
            } else {
                map.put(n, true);
            }
        }
        System.out.println(answer);
    }
}
