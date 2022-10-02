package baekjoon.IGRUS_Newbie_Programming_Contest_Open2022;

import java.io.*;

public class MainA {

    static int N;
    static BufferedWriter bw;

    public static void solve(int k) throws IOException {
        if (k == N) return;

        if (k == 0) {
            bw.write("int *ptr = &a;");
            bw.newLine();
        } else if (k == 1) {
            bw.write("int **ptr2 = &ptr;");
            bw.newLine();
        } else {
            bw.write("int ");
            for (int i = 0; i < k + 1; i++) {
                bw.write('*');
            }
            bw.write("ptr" + (k + 1));
            bw.write(" = &ptr" + k + ";");
            bw.newLine();
        }

        solve(k + 1);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/baekjoon/IGRUS_Newbie_Programming_Contest_Open2022/A.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        bw.write("int a;");
        bw.newLine();

        solve(0);

        bw.flush();
        bw.close();
    }
}
