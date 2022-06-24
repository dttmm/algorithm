package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main19947 {
    static int H;
    static int Y;
    static int[] D;

    public static void solve() {
        for (int i = 1; i <= Y; i++) {
            int one = 0;
            int three = 0;
            int five = 0;
            if (i >= 1) {
                one = (int) (D[i - 1] * 1.05);
                D[i] = Math.max(D[i], one);
            }
            if (i >= 3) {
                three = (int) (D[i - 3] * 1.2);
                D[i] = Math.max(D[i], three);
            }
            if (i >= 5) {
                five = (int) (D[i - 5] * 1.35);
                D[i] = Math.max(D[i], five);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/19947.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = new int[11];
        D[0] = H;

        solve();

        System.out.println(D[Y]);
    }
}
