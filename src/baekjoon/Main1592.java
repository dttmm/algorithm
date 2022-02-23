package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1592 {
    static int N;
    static int M;
    static int L;
    static int[] count;

    public static boolean check() {
        for (int i = 0; i < N; i++) {
            if (count[i] == M) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1592.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        count = new int[N];

        int sum = 0;
        int cur = 0;
        boolean dir = true; // true 시계방향
        while (check()) {
            count[cur]++;
            if (count[cur] % 2 == 0) {
                dir = false;
                cur = (N + cur - L) % N;
            } else {
                dir = true;
                cur = (N + cur + L) % N;
            }
            sum++;

        }
        System.out.println(sum - 1);
    }
}
