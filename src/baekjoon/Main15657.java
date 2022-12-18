package baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 간단한 중복 조합 문제에 간단한 정렬을 곁들인 문제
 */
public class Main15657 {

    static int N;
    static int M;
    static int[] arr;
    static int[] tr;
    static BufferedWriter bw;

    static void c(int k, int start) throws Exception {
        if (k == M) {
            for (int i = 0; i < M; i++) {
                bw.write(tr[i] + " ");
            }
            bw.newLine();
        } else {
            for (int i = start; i < N; i++) {

                tr[k] = arr[i];
                c(k + 1, i);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/15657.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        tr = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        c(0, 0);

        bw.flush();
        bw.close();
    }
}
