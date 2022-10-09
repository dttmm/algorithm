package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.HashSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class 기본개념_hashset_기본 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            switch (s) {
                case "find": {
                    bw.write(set.contains(n) + "");
                    bw.newLine();
                    break;
                }
                case "add": {
                    set.add(n);
                    break;
                }
                default: {
                    set.remove(n);
                }
            }
        }

        bw.flush();
        bw.close();
    }
}

