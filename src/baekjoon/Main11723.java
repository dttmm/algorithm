package baekjoon;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main11723 {

    static int getN(String s) {
        return Integer.parseInt(s);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11723.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet();
        Set<Integer> setAll = new HashSet();
        for (int i = 1; i <= 20; i++) {
            setAll.add(i);
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            switch (command) {
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
                case "check": {
                    int n = getN(st.nextToken());
                    if (set.contains(n)) bw.write(1 + "\n");
                    else bw.write(0 + "\n");
                    break;
                }
                case "toggle": {
                    int n = getN(st.nextToken());
                    if (set.contains(n)) set.remove(n);
                    else set.add(n);
                    break;
                }
                case "all": {
                    set.addAll(setAll);
                    break;
                }
                case "empty": {
                    set = new HashSet();
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
