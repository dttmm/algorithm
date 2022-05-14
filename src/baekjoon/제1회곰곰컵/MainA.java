package baekjoon.제1회곰곰컵;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainA {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/제1회곰곰컵/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int coke = Integer.parseInt(st.nextToken());
        int drink = Integer.parseInt(st.nextToken());

        int total = coke / 2 + drink;
        if (total > N) total = N;
        System.out.println(total);
    }
}
