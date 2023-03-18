package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 백준 대회에서 힘빠져서
 간단한 문제 풀어봄
 treeset으로 한방에 딱 끝
 */
public class Main10867 {

    static int N;
    static TreeSet<Integer> set;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10867.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        set = new TreeSet();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
        }

        StringBuilder sb = new StringBuilder();
        for (int n : set) {
            sb.append(n + " ");
        }

        System.out.println(sb);
    }
}
