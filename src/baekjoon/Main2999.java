package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2999 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2999.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int C = s.length();
        int R = 1;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.length() % i == 0) {
                if (i >= s.length() / i) {
                    C = i;
                    R = s.length() / i;
                }
            }
        }
        char[][] arr = new char[R][C];
        for (int j = 0; j < C; j++) {
            for (int i = 0; i < R; i++) {
                arr[i][j] = s.charAt(j * R + i);

            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(arr[i][j]);
            }
        }
    }
}
