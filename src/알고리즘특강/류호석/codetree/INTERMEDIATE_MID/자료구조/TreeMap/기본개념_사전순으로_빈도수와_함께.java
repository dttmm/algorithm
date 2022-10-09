package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.TreeMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class 기본개념_사전순으로_빈도수와_함께 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new TreeMap();

        for (int i = 0; i < N; i++) {
            String key = br.readLine();
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        for (String key : map.keySet()) {
            int value = map.get(key);
            Double d = (double) value / N * 100;
            bw.write(String.format("%s %.4f", key, d));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}

