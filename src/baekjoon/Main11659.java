package baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Main11659 {

    static int N;
    static int M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11659.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());

            sum += n;
            arr[i] = sum;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            bw.write(arr[end] - arr[start - 1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
