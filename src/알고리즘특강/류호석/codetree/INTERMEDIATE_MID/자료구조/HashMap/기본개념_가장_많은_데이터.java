package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.HashMap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 기본개념_가장_많은_데이터 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if (map.containsKey(s)) map.put(s, map.get(s) + 1);
            else map.put(s, 1);
        }

        int max = 0;
        for (int i : map.values()) {
            max = Math.max(max, i);
        }

        System.out.println(max);
    }
}

