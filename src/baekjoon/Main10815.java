package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 간단한 map, set문제

 오늘 알고컨디션 별로 좋지 않아서 쉬운문제..
 */
public class Main10815 {

    static int N;
    static int M;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10815.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        set = new HashSet();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int n = Integer.parseInt(st.nextToken());

            if (set.contains(n)) sb.append("1 ");
            else sb.append("0 ");
        }

        System.out.println(sb);
    }
}
