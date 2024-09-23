package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main29715 {

    static int N;
    static int M;
    static int X;
    static int Y;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/29715.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int count = 0;
        int sub = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a != 0) count++;
            else sub++;
        }

        int time = 1;
        for (int i = sub; i > 0; i--) {
            time *= i;
            count++;
        }
        for (int i = N - count; i > 0; i--) {
            time *= 9 - count;
            count++;
        }

        int answer = time * X;

        answer += ((time - 1) / 3) * Y;

        System.out.println(answer);
    }
}
