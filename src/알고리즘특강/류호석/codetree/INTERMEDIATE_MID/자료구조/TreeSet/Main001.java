package 알고리즘특강.류호석.codetree.INTERMEDIATE_MID.자료구조.TreeSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main001 {

    static int getN(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        // Your Program Goes Here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        TreeSet<Integer> set = new TreeSet();

        for (int t = 1; t <= N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "add": {
                    int n = getN(st.nextToken());

                    set.add(n);
                    break;
                }
                case "remove": {
                    int n = getN(st.nextToken());

                    if (set.contains(n)) set.remove(n);
                    break;
                }
                case "find": {
                    int n = getN(st.nextToken());

                    if (set.contains(n)) bw.write("true\n");
                    else bw.write("false\n");
                    break;
                }
                case "lower_bound": {
                    int n = getN(st.nextToken());

                    try {
                        int r = set.ceiling(n);
                        bw.write(r + "\n");
                    } catch (Exception e) {
                        bw.write("None\n");
                    }

                    break;
                }
                case "upper_bound": {
                    int n = getN(st.nextToken());

                    try {
                        int r = set.higher(n);
                        bw.write(r + "\n");
                    } catch (Exception e) {
                        bw.write("None\n");
                    }
                    break;
                }
                case "largest": {

                    try {
                        int r = set.last();
                        bw.write(r + "\n");
                    } catch (Exception e) {
                        bw.write("None\n");
                    }
                    break;
                }
                case "smallest": {

                    try {
                        int r = set.first();
                        bw.write(r + "\n");
                    } catch (Exception e) {
                        bw.write("None\n");
                    }
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }
}

