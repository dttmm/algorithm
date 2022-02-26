package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main9655 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/9655.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N % 2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}
