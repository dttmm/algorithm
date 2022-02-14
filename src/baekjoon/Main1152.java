package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1152 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1152.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        while (st.hasMoreTokens()) {
            st.nextToken();
            count++;
        }
        System.out.println(count);
    }
}
