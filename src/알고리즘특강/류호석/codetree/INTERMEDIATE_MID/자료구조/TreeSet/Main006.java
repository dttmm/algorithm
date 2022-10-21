package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.TreeSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main006 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        TreeSet<Integer> set = new TreeSet();
        set.add(0);

        int min = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);

            Object left = set.lower(n);
            Object right = set.higher(n);

            if (left != null) {
                int diff = n - (int) left;
                min = Math.min(min, diff);
            }
            if (right != null) {
                int diff = (int) right - n;
                min = Math.min(min, diff);
            }

            bw.write(min + "\n");
        }

        bw.flush();
        bw.close();
    }
}

