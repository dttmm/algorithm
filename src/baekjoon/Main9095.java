package baekjoon;

import java.io.*;

public class Main9095 {

    static int[] arr;

    static int solve(int n) {
        if (n <= 3) return arr[n];

        if (arr[n] == 0) {
            arr[n] = solve(n - 1) + solve(n - 2) + solve(n - 3);
        }

        return arr[n];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9095.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        arr = new int[12];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int result = solve(n);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
    }
}
