package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1157 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1157.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] arr = new int[2][26];
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                arr[0][c - 'a']++;
            } else {
                arr[1][c - 'A']++;
            }
        }
        int max = 0;
        int max_idx = 0;
        for (int i = 0; i < 26; i++) {
            int sum = arr[0][i] + arr[1][i];
            if (sum > max) {
                max = sum;
                max_idx = i;
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            int sum = arr[0][i] + arr[1][i];
            if (sum == max) count++;
        }
        if (count == 1) {
            System.out.println((char) ((char) max_idx + 'A'));
        } else {
            System.out.println("?");
        }
    }
}
