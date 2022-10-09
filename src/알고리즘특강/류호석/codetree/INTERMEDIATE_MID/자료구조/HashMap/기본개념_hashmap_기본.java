package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.HashMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 기본개념_hashmap_기본 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "add": {
                    int key = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    map.put(key, value);
                    break;
                }
                case "find": {
                    int key = Integer.parseInt(st.nextToken());
                    if (map.containsKey(key)) bw.write(map.get(key) + "");
                    else bw.write("None");
                    bw.newLine();
                    break;
                }
                default: {
                    int key = Integer.parseInt(st.nextToken());
                    map.remove(key);
                }
            }
        }

        bw.flush();
        bw.close();
    }
}

