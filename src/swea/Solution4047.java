package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution4047 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/swea/4047.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case + " ");
            String s = br.readLine();
            int[][] arr = new int[4][14];
            for (int i = 0; i < 4; i++) {
                arr[i][0] = 13;
            }

            boolean flag = true;
            for (int i = 0; i < s.length() / 3; i++) {
                char c = s.charAt(3 * i);
                int X = s.charAt(3 * i + 1) - '0';
                int Y = s.charAt(3 * i + 2) - '0';

                int shape;
                if (c == 'S') shape = 0;
                else if (c == 'D') shape = 1;
                else if (c == 'H') shape = 2;
                else shape = 3;
                if (X == 0) {
                    if (arr[shape][Y] != 0) {
                        System.out.print("ERROR");
                        flag = false;
                        break;
                    }
                    arr[shape][Y]++;
                } else {
                    if (arr[shape][10 * X + Y] != 0) {
                        System.out.print("ERROR");
                        flag = false;
                        break;
                    }
                    arr[shape][10 * X + Y]++;
                }
                arr[shape][0]--;
            }
            if (flag) {
                for (int i = 0; i < 4; i++) {
                    System.out.print(arr[i][0] + " ");
                }
            }
            System.out.println();
        }
    }
}