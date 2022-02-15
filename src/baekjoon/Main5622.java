package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main5622 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/5622.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split("");
        String[] arr = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

        int result = 0;
        for (int i = 0; i < s.length; i++) {
            String c = s[i];
            for (int j = 2; j <= 9; j++) {
                String word = arr[j];
                if (word.contains(c)) {
                    result += j+1;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
