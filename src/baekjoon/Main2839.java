package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2839 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/2839.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = N / 5;
        int sum = 0;
        for (int five = k; five >= 0; five--) {
            sum = 0;
            int cur = N - 5 * five;
            sum += five;
            int three = cur / 3;
            sum += three;
            if (cur % 3 != 0) {
                sum = -1;
                continue;
            } else {
                break;
            }
        }
        System.out.println(sum);
    }
}
