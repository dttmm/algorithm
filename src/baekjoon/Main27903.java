package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main27903 {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        if (n == 2) {
            System.out.println("2 3");
        } else if (n == 3) {
            System.out.println("43 37 31");
        }
    }
}
