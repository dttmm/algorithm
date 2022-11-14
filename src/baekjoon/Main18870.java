package baekjoon;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main18870 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/18870.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> set = new TreeSet();
        Map<Integer, Integer> map = new HashMap();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        int index = 0;
        while (!set.isEmpty()) {
            int n = set.pollFirst();
            map.put(n, index++);
        }

        st = new StringTokenizer(input);
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            bw.write(map.get(n) + " ");
        }

        bw.flush();
        bw.close();

    }
}
