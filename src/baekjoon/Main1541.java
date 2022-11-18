package baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main1541 {

    static int sumFormula(String s) {
        String[] ss = s.split("\\+");
        int sum = 0;
        for (int i = 0; i < ss.length; i++) {
            sum += Integer.parseInt(ss[i]);
        }

        return sum;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/baekjoon/1541.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        // -를 기준으로 자른다
        String[] ss = s.split("-");
        int sum = sumFormula(ss[0]);
        for (int i = 1; i < ss.length; i++) {
            // +를 기준으로 수를 나누어서 더한 결과를 더한다
            sum -= sumFormula(ss[i]);
        }

        System.out.println(sum);
    }
}
