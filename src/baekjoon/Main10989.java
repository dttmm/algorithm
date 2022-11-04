package baekjoon;

import java.io.*;

public class Main10989 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/10989.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[10001];

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            arr[n]++;
        }

        for (int i = 0; i <= 10000; i++) {
            while (arr[i] != 0) {
                bw.write(i + "\n");
                arr[i]--;
            }
        }

        bw.flush();
        bw.close();
    }
}
