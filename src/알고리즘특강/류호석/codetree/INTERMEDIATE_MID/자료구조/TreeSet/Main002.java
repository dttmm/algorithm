package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.TreeSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main002 {

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            TreeSet<Integer> set = new TreeSet();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                if (st.nextToken().equals("I")) {
                    int n = Integer.parseInt(st.nextToken());

                    set.add(n);
                } else {
                    if (set.isEmpty()) continue;
                    int n = Integer.parseInt(st.nextToken());

                    if (n == 1) {
                        set.remove(set.last());
                    } else {
                        set.remove(set.first());
                    }
                }
            }

            if (set.isEmpty()) bw.write("EMPTY\n");
            else {
                bw.write(set.last() + " " + set.first() + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}

