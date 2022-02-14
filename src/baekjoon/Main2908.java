package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2908 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2908.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        String n = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            n += s.charAt(i);
        }
        int n1 = Integer.parseInt(n);

        s = st.nextToken();
        n = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            n += s.charAt(i);
        }
        int n2 = Integer.parseInt(n);

        int max = n1 > n2 ? n1 : n2;
        System.out.println(max);
    }
}
