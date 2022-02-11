package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main11654 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/11654.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char c = br.readLine().charAt(0);
        System.out.println((int) c);
    }
}
